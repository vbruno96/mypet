// cor de fundo do menu
let menu = document.getElementById("menu");
window.onscroll = () => {
  if (document.documentElement.scrollTop >= 200) {
    menu.classList.add("bg-menu-show");
    menu.classList.remove("bg-menu-hide");
  } else {
    menu.classList.remove("bg-menu-show");
    menu.classList.add("bg-menu-hide");
  }
};
