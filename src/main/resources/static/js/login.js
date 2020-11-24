 let containerLoginRegister = document.getElementById("openCloseLogin");
 containerLoginRegister.addEventListener("click", () => {
   document
     .getElementById("containerLoginRegister")
     .classList.toggle("formHide");
 });

var loggedUser = null;

function submitLogin(e) {
  if (e) e.preventDefault();

  email = document.getElementById("inputLoginEmail").value;
  password = document.getElementById("inputLoginPassword").value;

  wrongUserPassField = document.getElementById("wrongUserPass");
  wrongUserPassField.style.display = "none";

  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/login", true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify({
    "username": email,
    "password": password
  }));

  xhr.onreadystatechange = function () {
    if (this.readyState != 4) return;

    if (this.status == 200) {
      var data = JSON.parse(this.responseText);
      localStorage.setItem("jwtKey", "Bearer " + data.jwt);
      openCloseLogin()
//      getLoggedUser();

      document.getElementById("inputLoginEmail").value = "";
      document.getElementById("inputLoginPassword").value = "";

      document.getElementById("inputRegisterName").value = "";
      document.getElementById("inputRegisterEmail").value = "";
      document.getElementById("inputRegisterPassword").value = "";
      document.getElementById("inputRegisterPasswordConfirm").value = "";
    } else if (this.status == 404) {
      document.getElementById("wrongUserPass").style.display = "block";
    }
  };
}

function submitRegister(e) {
  e.preventDefault();

  name = document.getElementById("inputRegisterName").value;
  email = document.getElementById("inputRegisterEmail").value;
  password = document.getElementById("inputRegisterPassword").value;
  passwordConfirm = document.getElementById("inputRegisterPasswordConfirm").value;

  document.getElementById("inputLoginEmail").value = email;
  document.getElementById("inputLoginPassword").value = password;

  userAlreadyExists = document.getElementById("userAreadyExists");
  userAlreadyExists.style.display = "none";

  passNotMatch = document.getElementById("passNotMatch");
  if (password != passwordConfirm) {
    passNotMatch.style.display = "block";
    return;
  }
  passNotMatch.style.display = "none";

  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/usuarios", true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify({
    "nome": name,
    "email": email,
    "senha": password
  }));

  xhr.onreadystatechange = function () {
    if (this.readyState != 4) return;

    if (this.status == 201) {
      submitLogin();
    } else if (this.status == 406) {
      document.getElementById("userAreadyExists").style.display = "block";
    }
  };

}

//function getLoggedUser() {
//  const jwtKey = localStorage.getItem("jwtKey");
//
//  if (!jwtKey) {
//    return;
//  }
//
//  var xhr = new XMLHttpRequest();
//  xhr.open("GET", "/usuarios", true);
//  xhr.setRequestHeader('Authorization', jwtKey);
//  xhr.send();
//
//  xhr.onreadystatechange = function () {
//    if (this.readyState != 4) return;
//
//    if (this.status == 200) {
//      var data = JSON.parse(this.responseText);
//      loggedUser = data;
//      document.getElementById("entrarButton").style.display = "none";
//      document.getElementById("perfilButton").style.display = "block";
//      document.getElementById("logoutButton").style.display = "block";
//    }
//    if (this.status == 403) {
//      logoutUser();
//    }
//
//  };
//}

function logoutUser() {
  localStorage.removeItem("jwtKey");
  document.getElementById("entrarButton").style.display = "block";
  document.getElementById("perfilButton").style.display = "none";
  document.getElementById("logoutButton").style.display = "none";

  document.getElementById("inputLoginEmail").value = "";
  document.getElementById("inputLoginPassword").value = "";

  document.getElementById("inputRegisterName").value = "";
  document.getElementById("inputRegisterEmail").value = "";
  document.getElementById("inputRegisterPassword").value = "";
  document.getElementById("inputRegisterPasswordConfirm").value = "";
}

function openCloseLogin() {
  document
    .getElementById("containerLoginRegister")
    .classList.toggle("formHide");
}

function moveFormsContainer() {
  document
    .getElementById("formsContainer")
    .classList.toggle("formsContainerMoveLeft");

  document
    .getElementById("formsContainer")
    .classList.toggle("formsContainerMoveRight");

  document.getElementById("formLogin").classList.toggle("formHide");

  document.getElementById("formRegister").classList.toggle("formHide");
}

function moveContentMobile() {
  document
    .getElementById("buttonMobile")
    .classList.toggle("moveContentMobileButtonBottom");

  if (
    document.getElementById("buttonMobile").textContent !==
    "Não tenho cadastro ▲"
  ) {
    document.getElementById("buttonMobile").textContent =
      "Não tenho cadastro ▲";
  } else {
    document.getElementById("buttonMobile").textContent = "Já tenho login ▼";
  }

  document
    .getElementById("buttonMobile")
    .classList.toggle("moveContentMobileButtonTop");

  document
    .getElementById("contentMobile")
    .classList.toggle("moveContentMobileBottom");
  document
    .getElementById("contentMobile")
    .classList.toggle("moveContentMobileTop");
}

document.getElementById("formLogin").onsubmit = submitLogin;
document.getElementById("formRegister").onsubmit = submitRegister;

//window.onload = getLoggedUser;