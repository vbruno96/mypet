let heigth = document.querySelector('body').offsetHeight
let dashboard = document.querySelector('.container')

dashboard.style.height = `${heigth-80}px`

var petFilter = {
    "especie": ["gato", "cachorro"],
    "tipoPelo": ["C", "L"],
    "personalidade": ["C", "B"],
    "sexo": ["M", "F"],
    "porte": ["P", "M", "G"]
  };

let tipoGato = document.querySelector('.gato')
let tipoCachorro  = document.querySelector('.cachorro')

tipoCachorro.addEventListener('click', ()=>{
    tipoCachorro.classList.toggle('selected')

    if (tipoCachorro.classList.contains("selected"))
        petFilter.especie.push("cachorro");
    else
        petFilter.especie = petFilter.especie.filter(item => item != "cachorro");
    fetchPets(true);
})

tipoGato.addEventListener('click', ()=>{
    tipoGato.classList.toggle('selected')
    
    if (tipoGato.classList.contains("selected"))
        petFilter.especie.push("gato");
    else
        petFilter.especie = petFilter.especie.filter(item => item != "gato");
    fetchPets(true);
})


 //Marca selecionado para Pelagem

let pelagemCurta = document.querySelector('.curto');
let pelagemLonga  = document.querySelector('.longa');

pelagemCurta.addEventListener('click', ()=>{
    pelagemCurta.classList.toggle('selected');

    if (pelagemCurta.classList.contains("selected"))
        petFilter.tipoPelo.push("C");
    else
        petFilter.tipoPelo = petFilter.tipoPelo.filter(item => item != "C");
    fetchPets(true);
})

pelagemLonga.addEventListener('click', ()=>{
    pelagemLonga.classList.toggle('selected');
    
    if (pelagemLonga.classList.contains("selected"))
        petFilter.tipoPelo.push("L");
    else
        petFilter.tipoPelo = petFilter.tipoPelo.filter(item => item != "L");
    fetchPets(true);
})


// Marca selecionado para Personalidade

let personalidadeCalmo = document.querySelector('.calmo')
let personalidadeBrincalhao  = document.querySelector('.brincalhao')

personalidadeCalmo.addEventListener('click', ()=>{
    personalidadeCalmo.classList.toggle('selected');
    
    if (personalidadeCalmo.classList.contains("selected"))
        petFilter.personalidade.push("C");
    else
        petFilter.personalidade = petFilter.personalidade.filter(item => item != "C");
    fetchPets(true);
})

personalidadeBrincalhao.addEventListener('click', ()=>{
    personalidadeBrincalhao.classList.toggle('selected');
    
    if (personalidadeBrincalhao.classList.contains("selected"))
        petFilter.personalidade.push("B");
    else
        petFilter.personalidade = petFilter.personalidade.filter(item => item != "B");
    fetchPets(true);
})

// Marca selecionado para sexo

let sexoMacho = document.querySelector('.macho');
let sexofemea  = document.querySelector('.femea');

sexoMacho.addEventListener('click', ()=>{
    sexoMacho.classList.toggle('selected');
    
    if (sexoMacho.classList.contains("selected"))
        petFilter.sexo.push("M");
    else
        petFilter.sexo = petFilter.sexo.filter(item => item != "M");
    fetchPets(true);
})

sexofemea.addEventListener('click', ()=>{
    sexofemea.classList.toggle('selected')
    
    if (sexofemea.classList.contains("selected"))
        petFilter.sexo.push("F");
    else
        petFilter.sexo = petFilter.sexo.filter(item => item != "F");
    fetchPets(true);
})

// Marca selecionado para porte

let porteP = document.querySelector('.pequeno');
let porteM  = document.querySelector('.medio');
let porteG  = document.querySelector('.grande');

porteP.addEventListener('click', ()=>{
    porteP.classList.toggle('selected');
    
    if (porteP.classList.contains("selected"))
        petFilter.porte.push("P");
    else
        petFilter.porte = petFilter.porte.filter(item => item != "P");
    fetchPets(true);
})

porteM.addEventListener('click', ()=>{
    porteM.classList.toggle('selected');

    if (porteM.classList.contains("selected"))
        petFilter.porte.push("M");
    else
        petFilter.porte = petFilter.porte.filter(item => item != "M");
    fetchPets(true);
})

porteG.addEventListener('click', ()=>{
    porteG.classList.toggle('selected');

    if (porteG.classList.contains("selected"))
        petFilter.porte.push("G");
    else
        petFilter.porte = petFilter.porte.filter(item => item != "G");
    fetchPets(true);
})

function initializeFilters() {
    tipoGato.classList.add("selected");
    tipoCachorro.classList.add("selected");
    
    pelagemCurta.classList.add("selected");
    pelagemLonga.classList.add("selected");

    personalidadeCalmo.classList.add("selected");
    personalidadeBrincalhao.classList.add("selected");

    sexoMacho.classList.add("selected");
    sexofemea.classList.add("selected");

    porteP.classList.add("selected");
    porteM.classList.add("selected");
    porteG.classList.add("selected");
}

// Animação Card 
let like = document.querySelector('.btn-like');
let dislike = document.querySelector('.btn-dislike');
let card = document.querySelector('.card');

function getLoggedUser() {
    const jwtKey = localStorage.getItem("jwtKey");

    if (!jwtKey) {
        window.location.replace("/index.html");
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/usuarios", true);
    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.send();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var data = JSON.parse(this.responseText);
            loggedUser = data;
            document.getElementById("entrarButton").style.display = "none";
            document.getElementById("perfilButton").style.display = "block";
            document.getElementById("logoutButton").style.display = "block";
        }
        if (this.status == 403) {
            window.location.replace("/index.html");
        }
    };
}

function logoutUser() {
    localStorage.removeItem("jwtKey");
    window.location.replace("/index.html");
}

//Insere um pet na card
function addPetCard(pet) {
    let link_imagem = [pet.link_imagem_1, pet.link_imagem_2, pet.link_imagem_3].find( a => a != null);
    document.querySelector("#img").style.backgroundImage = "url('" + link_imagem + "')";
}

//Adquire um novo pet e insere na card
function getNewPet() {
    //Verifica se existem pets
    if (savedPets.length) {
        displayedPet = savedPets[0];
        addPetCard(displayedPet);
        savedPets.shift();
    } else {
        console.log("Não existem mais pets");
    }

}

//Busca pets no banco
function fetchPets(getPet = false) {
    const jwtKey = localStorage.getItem("jwtKey");

    if (!jwtKey) {
        window.location.replace("/index.html");
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/pets/adote?especie=" + petFilter.especie.join() + "&tipoPelo=" + petFilter.tipoPelo.join() + "&personalidade=" + petFilter.personalidade.join()
                                           + "&sexo=" + petFilter.sexo.join() + "&porte=" + petFilter.porte.join(), true);

    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.send(JSON.stringify(petFilter));

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var data = JSON.parse(this.responseText);
            savedPets = [];
            for (pet in data) {
                savedPets.push(data[pet]);
            }
            if (getPet) {
                getNewPet();
            }
        }
        if (this.status == 403) {
            window.location.replace("/index.html");
        }

    };
}

function cardSelectMovement() {
    card.classList.remove('entrada')
    setTimeout(function () {
        card.classList.remove('like')
        card.classList.add('entrada')
        getNewPet();
    }, 300)
}

function likePet() {
    card.classList.add('like')
    cardSelectMovement();

    var idPet = displayedPet.id;

    const jwtKey = localStorage.getItem("jwtKey");

    if (!jwtKey) {
        window.location.replace("/index.html");
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/usuarios/likes", true);

    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(idPet));

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 204) {
            window.location.replace("/petPerfil.html?idPet=" + idPet);
        }
        if (this.status == 403) {
            window.location.replace("/index.html");
        }

    };
}

function dislikePet() {
    card.classList.add('saida')
    cardSelectMovement();
}

let savedPets = [];
let displayedPet = [];

window.onload = () => {
    getLoggedUser();
    initializeFilters();
    fetchPets(true)
};