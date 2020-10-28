let heigth  = document.querySelector('body').offsetHeight
let dashboard = document.querySelector('.container')

dashboard.style.height = `${heigth-80}px`

// Animação Card 
let like = document.querySelector('.btn-like')
let dislike = document.querySelector('.btn-dislike')
let card = document.querySelector('.card')

//função para trocar card 
function trocarCard(){
    card.classList.remove('entrada')
    card.classList.remove('like')
    card.classList.add('saida')

    setTimeout(function(){
        
        card.classList.remove('saida')
        card.classList.add('entrada')
    

    },700)
}


function trocarCardLike(){
    card.classList.remove('like')
    card.classList.remove('entrada')
    setTimeout(function(){
        card.classList.add('like')
        setTimeout(function(){
            card.classList.remove('like')
            card.classList.add('entrada')
        },600)
    },100)
}

dislike.addEventListener('click', function(){
    trocarCard()
})

like.addEventListener('click', function(){
    trocarCardLike()
})

// Item selecionado 
let filtro = document.querySelector('.filtro')
let likes = document.querySelector('.likes')

filtro.addEventListener('click', function(){
    likes.classList.remove('active')
    filtro.classList.add('active')
})

likes.addEventListener('click', function(){
    filtro.classList.remove('active')
    likes.classList.add('active')
})