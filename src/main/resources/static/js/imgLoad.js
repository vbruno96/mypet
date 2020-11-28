const reader = new FileReader();
const fileInput = document.querySelector("#img");
const img = document.querySelector(".img-perfil");

reader.onload = e => {
  img.src = e.target.result;
}
fileInput.addEventListener('change', e => {
  const f = e.target.files[0];
  reader.readAsDataURL(f);
})