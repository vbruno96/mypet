const fileInput = document.querySelector("#fotoPerfil");

fileInput.addEventListener('change', e => {
  const file = e.target.files[0];
  uploadImageProfile(file);
})

function uploadImageProfile(image) {
  const jwtKey = localStorage.getItem("jwtKey");

  if (!jwtKey) {
      window.location.replace("/index.html");
  }

  var formData = new FormData();
  formData.append("userImage", image)

  var xhr = new XMLHttpRequest();

  xhr.open("POST", "/usuarios/imagemPerfil", true);
  xhr.setRequestHeader('Authorization', jwtKey);
  xhr.send(formData);

  xhr.onreadystatechange = function () {
      if (this.readyState != 4) return;

      if (this.status == 204) {
          getLoggedUser();
      }
      if (this.status == 403) {
          window.location.replace("/index.html");
      }
  };
}