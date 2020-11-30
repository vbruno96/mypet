
// passar para proxima parte do preenchimento 

let primeiroForm = document.querySelector('#imgPets')
let segundoForm = document.querySelector('#filtroPets')
let primeiro = document.querySelector('#primeiro')
let segundo = document.querySelector('#segundo')

function proximoForm(e){    
    e.preventDefault();

    segundo.classList.remove('none')
    primeiroForm.style.zIndex = '0'
    segundoForm.style.zIndex = '1'

    // Troca a descrição do passo-passo
    let descricao = document.querySelector('.desc-modal')
    descricao.innerHTML = "<h3>Agora conta um pouquinho dele pra gente</h3>"

}

// Mostra ou fecha o modal

function mostrarModal(pet){
    let modal = document.querySelector('#modal');
    modal.classList.add('mostrar');

    let titulo = document.querySelector(".titulo-modal")
    titulo.innerHTML = "<h2>Adicionar novo pet</h2>"

    // Verifica se está sendo realizada a edição de um Pet já cadastrado
    if (pet) {
      petEdicao = pet;

      for (index in images) {
        if (pet["link_imagem_" + (parseInt(index)+1) ]) {
          imageFields[index].parentElement.style.backgroundImage = "url('" + pet["link_imagem_" + (parseInt(index)+1) ] + "')";
          imageFields[index].style.visibility = "hidden";
        }
      }

      document.getElementById("nomePet").value = pet.nome;
      document.getElementById("especiePet").value = pet.especie.id;
      document.getElementById("peloPet").value = pet.tipoPelo;
      document.getElementById("personalidadePet").value = pet.personalidade;
      document.getElementById("sexoPet").value = pet.sexo;
      document.getElementById("portePet").value = pet.porte;

      titulo.innerHTML = "<h2>Editar pet</h2>"
    }

}


function fecharModal(){
    modal.classList.remove('mostrar');
    primeiroForm.style.zIndex = '1'
    segundoForm.style.zIndex = '0'
    segundo.classList.add('none')

    for (i in images) images[i] = null;    
    imageFields.forEach(field => {
      field.parentElement.style.backgroundImage = "";
      field.style.visibility = "visible";
    })

    document.getElementById("nomePet").value = "";
    document.getElementById("especiePet").value = "1";
    document.getElementById("peloPet").value = "C";
    document.getElementById("personalidadePet").value = "C";
    document.getElementById("sexoPet").value = "M";
    document.getElementById("portePet").value = "P";
}


// Carrega o preview das imagens 

let petEdicao = null;

const reader = new FileReader();
var images = [null, null, null];

const imageInput1 = document.querySelector("#img1");
const imageInput2 = document.querySelector("#img2");
const imageInput3 = document.querySelector("#img3");

const imageFields = document.querySelectorAll(".img-pet");

imageInput1.addEventListener('change', e => {
  addImagePet(e, 1);
});
imageInput2.addEventListener('change', e => {
  addImagePet(e, 2);
});
imageInput3.addEventListener('change', e => {
  addImagePet(e, 3);
});

function addImagePet(e, id){
  const file = e.target.files[0];

  images[id-1] = file;

  reader.onload = (e => {
    const result = e.target.result;
    imageFields[id-1].parentElement.style.backgroundImage = "url('" + result + "')";
    imageFields[id-1].style.visibility = "hidden";
  });

  reader.readAsDataURL(file);
}

function cadastrarPet(e) {
  e.preventDefault();

  nome = document.getElementById("nomePet").value;
  especie = document.getElementById("especiePet").value;  
  pelo = document.getElementById("peloPet").value;
  personalidade = document.getElementById("personalidadePet").value;
  sexo = document.getElementById("sexoPet").value;
  porte = document.getElementById("portePet").value;

  const jwtKey = localStorage.getItem("jwtKey");
  
  if (!jwtKey) {
      window.location.replace("/index.html");
  }

  var xhr = new XMLHttpRequest();

  // Cadastro de novo pet
  if (document.querySelector(".titulo-modal").innerText == "Adicionar novo pet") {
    xhr.open("POST", "/pets", true);
  }
  // Edição de um pet
  else {
    xhr.open("PUT", "/pets/" + petEdicao.id, true);
  }
  
  xhr.setRequestHeader('Authorization', jwtKey);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify({
    "nome": nome,
    "sexo": sexo,
    "porte": porte,
    "personalidade": personalidade,
    "tipoPelo": pelo,
    "especie": {
      "id": especie
    }
  }));

  xhr.onreadystatechange = function () {
    if (this.readyState != 4) return;

    // Pet criado
    if (this.status == 201) {
      petId = this.getResponseHeader("location").split("/").pop();
      uploadPetImages(petId);
    }
    // Pet alterado
    else if (this.status == 204) {
      uploadPetImages(petEdicao.id);
    }
    else if (this.status == 403) {
      window.location.replace("/index.html");
    }

  };

}

function uploadPetImages(petId) {

  const jwtKey = localStorage.getItem("jwtKey");
  
  if (!jwtKey) {
      window.location.replace("/index.html");
  }

  for (index in images) {
    if (images[index] == null) continue;

    var formData = new FormData();
    formData.append("petImage", images[index])

    let xhr = new XMLHttpRequest();

    xhr.open("POST", "/pets/" + petId + "/imagem?idImagem=" + (parseInt(index)+1), true);
    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.send(formData);

    xhr.onreadystatechange = function () {
      getPets();
    };    
  }
  getPets();
  fecharModal();
}

document.getElementById("form-cadastro-pet").onsubmit = cadastrarPet;