var loggedUser = null;

function updateUserInfo() {
    document.getElementById("nomeField").value = loggedUser.nome;
    document.getElementById("emailField").value = loggedUser.login.email;
    document.getElementById("descricaoField").value = loggedUser.descricao;
    document.getElementById("telefoneField").value = loggedUser.telefone;
    document.getElementById("celularField").value = loggedUser.celular;
    document.getElementById("cepField").value = loggedUser.cep;
    document.getElementById("cidadeField").value = loggedUser.cidade;
    document.getElementById("bairroField").value = loggedUser.bairro;
    document.getElementById("logradouroField").value = loggedUser.logradouro;
    document.getElementById("numeroField").value = loggedUser.num_residencia;
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
      }
      else if (this.status == 403) {
        window.location.replace("/index.html");
      }
  
    };
  }

window.onload = getLoggedUser;