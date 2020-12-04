var loggedUser = null;
var pets = null;

let doacao = document.querySelector('.doacao');
let adocao = document.querySelector('.adocao2');
let doacaoContainer = document.querySelector('.container-pet-doacao');
let adocaoContainer = document.querySelector('.container-pet-adocao');

doacao.addEventListener('click', function(){
    adocaoContainer.style.zIndex = "0"
    doacaoContainer.style.zIndex  = "1"
})

adocao.addEventListener('click', function(){
    doacaoContainer.style.zIndex  = "0"
    adocaoContainer.style.zIndex = "1"
})


function updateUserInfo() {

}

function updatePetCards() {
    container = document.getElementById("container-pets");

    cards = document.querySelectorAll(".card");
    if (cards) cards.forEach(card => container.removeChild(card));

    pets.forEach( pet => {
        card = document.createElement("div");
        card.classList.add("card");

        imagem = pet.link_imagem_1 ? pet.link_imagem_1 : pet.link_imagem_2 ? pet.link_imagem_2 : pet.link_imagem_3;

        imageDiv = document.createElement("div");
        imageDiv.classList.add("img-pet");
        if (imagem) imageDiv.style.backgroundImage = "url('" + imagem + "')";
        card.appendChild(imageDiv);

        nomeDiv = document.createElement("div");
        nomeDiv.classList.add("nome-pet");
        nome = document.createElement("h2");
        nome.innerHTML = pet.nome;

        nomeDiv.appendChild(nome);
        card.appendChild(nomeDiv);

        likesDiv = document.createElement("div");
        likesDiv.classList.add("likes-pet");
        likes = document.createElement("p");
        likes.innerHTML = "Likes: " + pet.likes.length;

        likesDiv.appendChild(likes);
        card.appendChild(likesDiv);

        matchesDiv = document.createElement("div");
        matchesDiv.classList.add("match-pet");
        matches = document.createElement("p");
        matches.innerHTML = "Matches: " + pet.matches.length;

        matchesDiv.appendChild(matches);
        card.appendChild(matchesDiv);

        card.onclick = ( () => mostrarModal(pet));
        
        container.append(card);
    });
}

function logoutUser() {
    localStorage.removeItem("jwtKey");
    window.location.replace("/index.html");
}

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
            updateUserInfo();
        } else if (this.status == 403) {
            window.location.replace("/index.html");
        }

    };
}

function getPets() {
    const jwtKey = localStorage.getItem("jwtKey");

    if (!jwtKey) {
        window.location.replace("/index.html");
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/pets", true);
    xhr.setRequestHeader('Authorization', jwtKey);
    xhr.send();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var data = JSON.parse(this.responseText);
            pets = data;
            updatePetCards();
        } else if (this.status == 403) {
            window.location.replace("/index.html");
        }

    };
}

window.onload = ( () => {getLoggedUser(); getPets();});