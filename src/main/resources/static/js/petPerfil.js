let img = document.querySelectorAll('.img')
let imgSelecioinada = document.querySelector('#imgSelecionada')


img.forEach( (element, index) => {
    element.addEventListener('click', function(){
        if (!element.style.backgroundImage) {
            return;
        }
        
        imgSelecioinada.style.backgroundImage = element.style.backgroundImage;

        img.forEach((element) => {
            element.classList.remove('selecionada');
        })

        element.classList.add('selecionada');
    })
});


let fecharModal = document.querySelector('.fechar-btn')
let modal = document.querySelector('#modal');

fecharModal.addEventListener('click', function(){
    modal.classList.remove('mostrar')
})


function mostrarModal(pet){
    modal.classList.toggle('mostrar');
}

function displayPet(pet) {
    foundFirst = false;
    for (i = 1; i <= 3; i++) {
        let image = pet["link_imagem_" + i];
        let container = document.getElementById("img" + i);

        if (image) {
            container.style.backgroundImage = "url('" + image + "')";
            if (!foundFirst) {
                document.querySelector('#imgSelecionada').style.backgroundImage = "url('" + image + "')";
                document.querySelectorAll('.img').forEach((element) => {
                    element.classList.remove('selecionada');
                })

                container.classList.add('selecionada');

                foundFirst = true;
            }
        }
        
        document.getElementById("personalidade").innerHTML = pet.personalidade == "C" ? "Calmo" : "Brincalhão";
        document.getElementById("pelagem").innerHTML = pet.tipoPelo == "C" ? "Curto" : "Longo";
        document.getElementById("especie").innerHTML = pet.especie.nome;
        document.getElementById("porte").innerHTML = pet.porte == "P" ? "Pequeno" : pet.porte == "M" ? "Médio" : "Grande";
        document.getElementById("sexo").innerHTML = pet.sexo == "M" ? "Macho" : "Fêmea";
    }

}

function getDisplayPet() {
   const queryString = window.location.search;
   const urlParams = new URLSearchParams(queryString);

   idPet = urlParams.get('idPet');

   const jwtKey = localStorage.getItem("jwtKey");
 
   if (!jwtKey) {
       window.location.replace("/index.html");
   }
 
   var xhr = new XMLHttpRequest();
   xhr.open("GET", "/usuarios/likes", true);
   xhr.setRequestHeader('Authorization', jwtKey);
   xhr.send();
 
   xhr.onreadystatechange = function () {
     if (this.readyState != 4) return;
 
     if (this.status == 200) {
       var pets = JSON.parse(this.responseText);     
       document.getElementById("entrarButton").style.display = "none";
       document.getElementById("perfilButton").style.display = "block";
       document.getElementById("logoutButton").style.display = "block";

       petDisplay = pets.filter(pet => pet.id == idPet)[0];

       displayPet(petDisplay);
     }
     else if (this.status == 403) {
       window.location.replace("/index.html");
     }
 
   };
}

window.onload = getDisplayPet;
