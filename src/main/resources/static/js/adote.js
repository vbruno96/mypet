let heigth  = document.querySelector('body').offsetHeight
let dashboard = document.querySelector('.container')

dashboard.style.height = `${heigth-80}px`

// Animação Card 
let like = document.querySelector('.btn-like')
let dislike = document.querySelector('.btn-dislike')
let card = document.querySelector('.card')

// Item selecionado
let filtro = document.querySelector('.filtro')
let likes = document.querySelector('.likes')

filtro.addEventListener('click', function(){
    likes.classList.remove('active')
    filtro.classList.add('active')
})

likes.addEventListener('click', function(){
    filtro.classList.remove('active')
    likes.classList.add('active')
})

//Insere um pet na card
function addPetCard (pet) {
    let link_imagem = pet.imagensPet[0].link_imagem;
    document.querySelector("#imagem-pet").src= link_imagem;
}

//Adquire um novo pet e insere na card
function getNewPet() {
    //Verifica se existem pets
    if (savedPets.length) {
        displayedPet = savedPets[0];
        addPetCard(displayedPet);
        savedPets.shift();
    }
    else {
        console.log("Não existem mais pets");
    }

    //Verifica se ainda existem muitos pets salvos.
    //Caso contrário, busca novos pets.
    if (savedPets.length > 5) {        
        return;
    }

    fetchPets();
    
}

//Busca pets no banco
function fetchPets() {
    return fetch("http://localhost:8080/pets?page="+(currentPagePet++)+"&size=10").then(function (response) {
        return response.json();
    }).then(function (responseJson) {

        for (pet in responseJson.content) {
            savedPets.push(responseJson.content[pet]);
        }

    }).catch(function (error) {
        console.log("Error: " + error);
    });   
}

function cardSelectMovement() {
    card.classList.remove('entrada')
    setTimeout(function(){
        card.classList.remove('like')
        card.classList.add('entrada')
        getNewPet();
    },700)
}

function likePet() {
    card.classList.add('like')
    cardSelectMovement();
}

function dislikePet() {
    card.classList.add('saida')
    cardSelectMovement();  
}

let currentPagePet = 0;
let savedPets = [];
let displayedPet = [];
fetchPets().then( ()=> {getNewPet();});
