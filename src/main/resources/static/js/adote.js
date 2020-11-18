let heigth = document.querySelector('body').offsetHeight
let dashboard = document.querySelector('.container')

dashboard.style.height = `${heigth-80}px`

var petFilter = {
    "brincalhao": true,
    "cachorro": true,
    "calmo": true,
    "femea": true,
    "gato": true,
    "grande": true,
    "macho": true,
    "medio": true,
    "peloCurto": true,
    "peloLongo": true,
    "pequeno": true
  };

let tipoGato = document.querySelector('.gato')
let tipoCachorro  = document.querySelector('.cachorro')

tipoCachorro.addEventListener('click', ()=>{
    tipoCachorro.classList.toggle('selected')
    petFilter.cachorro = tipoCachorro.classList.contains("selected");
    fetchPets(true);
})

tipoGato.addEventListener('click', ()=>{
    tipoGato.classList.toggle('selected')
    petFilter.gato = tipoGato.classList.contains("selected");
    fetchPets(true);
})


// Marca selecionado para Pelagem

let pelagemCurta = document.querySelector('.curto');
let pelagemLonga  = document.querySelector('.longa');

pelagemCurta.addEventListener('click', ()=>{
    pelagemCurta.classList.toggle('selected');
    petFilter.peloCurto = pelagemCurta.classList.contains("selected");
    fetchPets(true);
})

pelagemLonga.addEventListener('click', ()=>{
    pelagemLonga.classList.toggle('selected');
    petFilter.peloLongo = pelagemLonga.classList.contains("selected");
    fetchPets(true);
})


// Marca selecionado para Personalidade

let personalidadeCalmo = document.querySelector('.calmo')
let personalidadeBrincalhao  = document.querySelector('.brincalhao')

personalidadeCalmo.addEventListener('click', ()=>{
    personalidadeCalmo.classList.toggle('selected');
    petFilter.calmo = personalidadeCalmo.classList.contains("selected");
    fetchPets(true);
})

personalidadeBrincalhao.addEventListener('click', ()=>{
    personalidadeBrincalhao.classList.toggle('selected');
    petFilter.brincalhao = personalidadeBrincalhao.classList.contains("selected");
    fetchPets(true);
})

// Marca selecionado para sexo

let sexoMacho = document.querySelector('.macho');
let sexofemea  = document.querySelector('.femea');

sexoMacho.addEventListener('click', ()=>{
    sexoMacho.classList.toggle('selected');
    petFilter.macho = sexoMacho.classList.contains("selected");
    fetchPets(true);
})

sexofemea.addEventListener('click', ()=>{
    sexofemea.classList.toggle('selected')
    petFilter.femea = sexofemea.classList.contains("selected");
    fetchPets(true);
})

// Marca selecionado para porte

let porteP = document.querySelector('.pequeno');
let porteM  = document.querySelector('.medio');
let porteG  = document.querySelector('.grande');

porteP.addEventListener('click', ()=>{
    porteP.classList.toggle('selected');
    petFilter.pequeno = porteP.classList.contains("selected");
    fetchPets(true);
})

porteM.addEventListener('click', ()=>{
    porteM.classList.toggle('selected');
    petFilter.medio = porteM.classList.contains("selected");
    fetchPets(true);
})

porteG.addEventListener('click', ()=>{
    porteG.classList.toggle('selected');
    petFilter.grande = porteG.classList.contains("selected");
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
            updateUserInfo();
        }
        if (this.status == 403) {
            window.location.replace("/index.html");
        }
    };
}

function updateUserInfo() {
    document.getElementById("nomeUser").innerHTML = loggedUser.nome;
    document.getElementById("imagemUser").src = "/userImages/" + loggedUser.link_imagem;
}

//Insere um pet na card
function addPetCard(pet) {
    let link_imagem = pet.imagensPet[0].link_imagem;
    document.querySelector("#imagem-pet").style.backgroundImage = "url('/petImages/" + link_imagem+"')";
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
    xhr.open("PATCH", "/pets", true);

    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.setRequestHeader('Content-Type', 'application/json');
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
    }, 700)
}

function likePet() {
    card.classList.add('like')
    cardSelectMovement();
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