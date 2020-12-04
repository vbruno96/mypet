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

let navbar = document.querySelector('nav')

let informacoes = document.querySelectorAll('.informacoes')
let informacoesLegais = document.querySelector('.informacoes-legais')

let box = document.querySelectorAll('.box')
let box2 = document.querySelector('.box2')
let box3 = document.querySelector('.box3')

let fotoFiltro = document.querySelectorAll('.filtro-foto', )

window.addEventListener('scroll', function(){
    if(window.pageYOffset >= navbar.offsetHeight){
        navbar.style.backgroundColor = "#1e1b26"
        navbar.style.borderBottom = "1px solid #fafafa0a"
    }else{
        navbar.style.backgroundColor = '#1e1b2611'
    }
})

window.addEventListener('scroll', function(){
    informacoes.forEach((element, index) => {
        
        if(element.offsetTop <= window.pageYOffset + navbar.offsetHeight){
          box[index].classList.add('active')
          box[index].style.left = "0"
          fotoFiltro[index].classList.add('hover')
      }
    });
})


window.addEventListener('scroll', function(){
    if(informacoes[1].offsetTop <=window.pageYOffset + navbar.offsetHeight ){
        box2.classList.add('active2')
        box2.style.right = "0"
    }
})

window.addEventListener('scroll', function(){
    if(informacoesLegais.offsetTop <=window.pageYOffset + navbar.offsetHeight ){
        box3.classList.add('active3')
        box3.style.top = "40%"
    }
})
