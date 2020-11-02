// let containerLoginRegister = document.getElementById("openCloseLogin");
// containerLoginRegister.addEventListener("click", () => {
//   document
//     .getElementById("containerLoginRegister")
//     .classList.toggle("formHide");
// });

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
