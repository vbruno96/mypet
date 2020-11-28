
// passar para proxima parte do preenchimento 

let primeiroForm = document.querySelector('#imgPets')
let segundoForm = document.querySelector('#filtroPets')
let primeiro = document.querySelector('#primeiro')
let segundo = document.querySelector('#segundo')

function proximoForm(e){
    
    e.preventDefault();

    segundo.classList.remove('none')
    primeiroForm.style.zIndex = '0'
    segundoForm.style.zIndex = '1'

    // troca a descrição do passo-passo
    let descricao = document.querySelector('.desc-modal')
    descricao.innerHTML = "<h3>Agora conta um pouquinho dele pra gente</h3>"

}

// mostra ou fecha o modal

function mostrarModal(){
    let modal = document.querySelector('#modal');
    modal.classList.add('mostrar');
}


function fecharModal(){
    let fecharBtn = document.querySelector('.btn-close-modal');
    modal.classList.remove('mostrar');
    primeiroForm.style.zIndex = '1'
    segundoForm.style.zIndex = '0'
    segundo.classList.add('none')
}


// carrega o preview das imagens 

const reader = new FileReader();
const img1 = document.querySelector("#img1");
const img2 = document.querySelector("#img2");
const img3 = document.querySelector("#img3");
const img = document.querySelectorAll(".img-pet");

console.log(img)

img1.addEventListener('click',function(){
    img1mostrar()
} )


img2.addEventListener('click',function(){
    img2mostrar()
} )

img3.addEventListener('click',function(){
    img3mostrar()
} )


function img1mostrar(){
    reader.onload = e => {
        img[0].src = e.target.result;
      }

      img1.addEventListener('change', e => {
        const f = e.target.files[0];
        reader.readAsDataURL(f);
      })
}

function img2mostrar(){
    reader.onload = e => {
        img[1].src = e.target.result;
      }
      img2.addEventListener('change', e => {
        const f = e.target.files[0];
        reader.readAsDataURL(f);
      })
      
}

function img3mostrar(){
    reader.onload = e => {
        img[2].src = e.target.result;
      }
      img3.addEventListener('change', e => {
        const f = e.target.files[0];
        reader.readAsDataURL(f);
      })
      
}


  