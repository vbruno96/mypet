var loggedUser = null;
var pets = null;

function updateUserInfo() {

}

function updatePetCards() {
    container = document.getElementById("container-pets");

    container.childNodes.forEach(child => {
        if (child.classList && !child.classList.contains("card-add")) {
            container.removeChild(child);
        }
    })

    pets.forEach( pet => {
        card = document.createElement("div");
        card.classList.add("card");

        imageDiv = document.createElement("div");
        imageDiv.classList.add("img-pet");
        imageDiv.style.backgroundImage = "url('" + pet.link_imagem + "')";
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