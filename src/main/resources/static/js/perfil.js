var loggedUser = null;

function logoutUser() {
  localStorage.removeItem("jwtKey");
  window.location.replace("/index.html");
}

function updateUserInfo() {
  document.getElementById("nome-display").innerHTML = loggedUser.nome;
  document.getElementById("nome").value = loggedUser.nome;
  document.getElementById("email").value = loggedUser.login.email;
  document.getElementById("descricao").value = loggedUser.descricao;
  document.getElementById("telefone").value = loggedUser.telefone;
  document.getElementById("celular").value = loggedUser.celular;
  document.getElementById("cep").value = loggedUser.cep;
  document.getElementById("cidade").value = loggedUser.cidade;
  document.getElementById("bairro").value = loggedUser.bairro;
  document.getElementById("logradouro").value = loggedUser.logradouro;
  document.getElementById("imagem").src = loggedUser.link_imagem;
}

function saveUser(e) {
  e.preventDefault();

  newUser = loggedUser;
  newUser.nome = document.getElementById("nome").value;
  loggedUser.login.email = document.getElementById("email").value;
  loggedUser.descricao = document.getElementById("descricao").value;
  loggedUser.telefone = document.getElementById("telefone").value;
  loggedUser.celular = document.getElementById("celular").value;
  loggedUser.cep = document.getElementById("cep").value;
  loggedUser.cidade = document.getElementById("cidade").value;
  loggedUser.bairro = document.getElementById("bairro").value;
  loggedUser.logradouro = document.getElementById("logradouro").value;

  sendNewUserInfo(newUser);
}

function sendNewUserInfo(user) {
  const jwtKey = localStorage.getItem("jwtKey");
  
  if (!jwtKey) {
      window.location.replace("/index.html");
  }

  var xhr = new XMLHttpRequest();
  xhr.open("PUT", "/usuarios", true);
  xhr.setRequestHeader('Authorization', jwtKey);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(user));

  xhr.onreadystatechange = function () {
    if (this.readyState != 4) return;

    if (this.status == 200) {
      getLoggedUser();
    }
    else if (this.status == 403) {
      window.location.replace("/index.html");
    }

  };
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
     }
     else if (this.status == 403) {
       window.location.replace("/index.html");
     }
 
   };
 }

//window.onload = getLoggedUser;
//
//document.getElementById("formUserData").onsubmit = saveUser;