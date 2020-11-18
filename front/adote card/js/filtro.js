let tipoGato = document.querySelector('.gato')
let tipoCachorro  = document.querySelector('.cachorro')

tipoCachorro.addEventListener('click', ()=>{
    tipoCachorro.classList.toggle('selected')
})

tipoGato.addEventListener('click', ()=>{
    tipoGato.classList.toggle('selected')
})


// Marca selecionado para Pelagem

let pelagemCurta = document.querySelector('.curto')
let pelagemLonga  = document.querySelector('.longa')

pelagemCurta.addEventListener('click', ()=>{
    pelagemCurta.classList.toggle('selected')
})

pelagemLonga.addEventListener('click', ()=>{
    pelagemLonga.classList.toggle('selected')
})


// Marca selecionado para Personalidade

let personalidadeCalmo = document.querySelector('.calmo')
let personalidadeBrincalhao  = document.querySelector('.brincalhao')

personalidadeCalmo.addEventListener('click', ()=>{
    personalidadeCalmo.classList.toggle('selected')
})

personalidadeBrincalhao.addEventListener('click', ()=>{
    personalidadeBrincalhao.classList.toggle('selected')
})

// Marca selecionado para sexo

let sexoMacho = document.querySelector('.macho')
let sexoFemia  = document.querySelector('.femia')

sexoMacho.addEventListener('click', ()=>{
    sexoMacho.classList.toggle('selected')
})

sexoFemia.addEventListener('click', ()=>{
    sexoFemia.classList.toggle('selected')
})

// Marca selecionado para porte

let porteP = document.querySelector('.pequeno')
let porteM  = document.querySelector('.medio')
let porteG  = document.querySelector('.grande')

porteP.addEventListener('click', ()=>{
    porteP.classList.toggle('selected')
})

porteM.addEventListener('click', ()=>{
    porteM.classList.toggle('selected')
})

porteG.addEventListener('click', ()=>{
    porteG.classList.toggle('selected')
})