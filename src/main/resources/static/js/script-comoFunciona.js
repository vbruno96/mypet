// capturando a classe Card no html
let card = document.querySelector('.card')

// capturando o item Figure do html com os itens 
let itemCard = document.querySelector('figure')

// lista de pet
let petLista = ['<lottie-player src="https://assets2.lottiefiles.com/packages/lf20_JBqzsg.json"  background="transparent"  speed="1"  style="width: 400px; height: 400px;"  loop  autoplay></lottie-player>',
                '<lottie-player src="https://assets2.lottiefiles.com/packages/lf20_7coErG.json"  background="transparent"  speed="1"  style="width: 400px; height: 400px;"  loop  autoplay></lottie-player>',
                '<lottie-player src="https://assets2.lottiefiles.com/packages/lf20_C31OHO.json"  background="transparent"  speed="1"  style="width: 400px; height: 400px;"  loop  autoplay></lottie-player>',
                '<lottie-player src="https://assets3.lottiefiles.com/packages/lf20_OT15QW.json"  background="transparent"  speed="1"  style="width: 400px; height: 400px;"  loop  autoplay></lottie-player>',
                '<lottie-player src="https://assets5.lottiefiles.com/packages/lf20_pNx6yH.json"  background="transparent"  speed="1"  style="width: 400px; height: 400px;"  loop  autoplay></lottie-player>',
                '<lottie-player src="https://assets4.lottiefiles.com/packages/lf20_mTZ56e.json"  background="transparent"  speed="1"  style="width: 400px; height: 400px;"  loop  autoplay></lottie-player>'
                ]
let quantidade = 0

// capturando o item com a Frase da descrição
let frase = document.querySelector('.chamada')

// capturando a frase presente dentro do card
let fraseCard = document.querySelector('.frase')

// controles de escolha 
let controles = {
                    controle: `  
                    <div class="controles">
                        <div class="like">
                            <i class="fas fa-check-circle fa-2x"></i>
                        </div>
                        <div class="deslike">
                            <i class="fas fa-times-circle fa-2x"></i>
                        </div>
                    </div>`
                }

// Array com cada item que será exibido, na sua respectiva ordem
let item=0
let cardItem = [
    {
        text: `<a href="#" onclick="openCloseLogin()">Cadastre-se agora</a>`, 

        fraseChamada: `É simples, primeiro faça o seu cadastro com a gente, é bem rapidinho...
                        <a href="#" onclick="trocaDeItem()">E depois?</a>`,

        item: `<lottie-player 
        src="https://assets7.lottiefiles.com/packages/lf20_dl87KC.json" 
        background="transparent"  
        speed="1"  
        style="width: 400px; 
        height: 400px;"  
        loop  autoplay>
        </lottie-player>`
           
    },
    {
        text: `<a href="#">Selecione os filtros</a>`, 

        fraseChamada: `Nos conte o que é importante no seu pet perfeito, e veja ele 
                        surgir como mágica. 

                        <div class="cardFiltro">
                            <input type="checkbox" name="tipo" id="tipo">
                            <label for="tipo">Fofo</label>
                            <input type="checkbox" name="porte" id="porte">
                            <label for="porte">Peludo</label>
                            <input type="checkbox" name="carinhoso" id="carinhoso">
                            <label for="carinhoso">Cheio de amor</label>
                        </div>

                        <a href="#" onclick="filtro()">Filtrar</a>`,

        item: `<lottie-player 
        src="https://assets9.lottiefiles.com/private_files/lf30_Jogniz.json"  
        background="transparent"  
        speed="1" 
        style="width: 400px; 
        height: 400px;"  
        loop>
        
        </lottie-player>`
           
    },
    {
        item: `<lottie-player
        src="https://assets10.lottiefiles.com/packages/lf20_Zz37yH.json"  
        background="transparent"  
        speed="1"  
        style="width: 400px;
        height: 400px;" 
        loop  
        autoplay>
        </lottie-player>`,

        fraseChamada: `Vamos te mostrar um por um,
                        os seus candidatos a amigo perfeito e 
                        você nos conta se gosta dele ou não`,

        text:''


    },
    {
        item: `<lottie-player src="https://assets7.lottiefiles.com/packages/lf20_F6GXBF.json"  
                    background="transparent"  
                    speed="1" 
                    style="width: 400px;
                    height: 400px;"  
                    loop  
                    autoplay>
                </lottie-player>`,

        fraseChamada: `Agora é com você, veja mais fotinhas do seu pet,
                        e também poderá entrar em contato com o doador 
                        para que possam marcar um encontro !
                        
                        <a href="#" onclick="trocaDeItem()">Mas e se não der match?</a>
                        `,

        text: 'Olha como ele é lindo'
    },
    {
        item: `<lottie-player 
                src="https://assets8.lottiefiles.com/datafiles/tb05IsAooEikSbt/data.json"  
                background="transparent"  
                speed="1"  
                style="width: 400px; 
                height: 400px;"  
                loop  
                autoplay>
               </lottie-player>`,

        fraseChamada: `Não se preocupe, os doadores de todos os pets que você curtiu 
                        serão informados e poderão entrar em contato com você
                        
                        <a href="./adote.html" >Encontrar meu pet perfeito</a>
                        `,

        text: 'E só aguardar'
    }
]



// Mostra o conteúdo assim que a página é carregada
window.onload = function(){
   
    
    card.classList.add('animate-rigth')
    frase.classList.add('animate-left')
    frase.style.left = "0px"

}

//interação com o card

function cardInterativo(){
    trocaDeItem()
    
}

//Oculta o card e chama a função para carregar novo item
function trocaDeItem(){
       
      card.classList.add('animate-fim')
      frase.classList.remove('animate-left')
      frase.classList.add('animate-saida')
    
      setTimeout(()=>{
          card.style.top = '150%'
          frase.style.left = '200%'
         novoItem()
      },400)
    
}

// Carrega o novo item dentro do card
function novoItem(){
    itemCard.innerHTML = ""
    itemCard.innerHTML = cardItem[item].item
    mostrarNovoItem()
}

// mostra o novo Item na página
function mostrarNovoItem(){

    if(cardItem[item] == cardItem[2]){
        fraseCard.innerHTML = controles.controle
        capturarControles()
    }
    else{
        fraseCard.innerHTML =  cardItem[item].text
    }

    
    frase.innerHTML = cardItem[item].fraseChamada

    card.classList.remove('animate-fim')
    card.classList.add('animate-rigth')

   
    frase.classList.remove('animate-saida')
    frase.classList.add('animate-left')
    card.style.top = '0px'
    frase.style.left = '0px'
    
    item++
    
    
}

function filtro(){
    itemCard.innerHTML =cardItem[item-1].item = `<lottie-player 
    src="https://assets9.lottiefiles.com/private_files/lf30_Jogniz.json"  
    background="transparent"  
    speed="1" 
    style="width: 400px; 
    height: 400px;"  
    autoplay
    loop>
    </lottie-player>`

    setTimeout(()=>{
        fraseCard.innerHTML =cardItem[item-1].text = 'Oi'

        frase.innerHTML = `Viu só, é simples e rápido.

        <a href="#" onclick="cardInterativo()">Mas o que faço agora?</a>`
    },2000)

   
}

//Verifica se houve clique no like ou deslike 
function capturarControles(){
   
    let like = document.querySelector('.like')
    let deslike = document.querySelector('.deslike')

    return likeOuDeslike(like,deslike)

}

function likeOuDeslike(like, deslike){

    like.addEventListener('click', function(){
        itemCard.innerHTML = `<lottie-player 
                                src="https://assets5.lottiefiles.com/datafiles/6Wpign7xaCU4LQE/data.json"  
                                background="transparent"  speed="1"  
                                style="width: 400px; 
                                height: 400px;" 
                                autoplay  >
                            </lottie-player>`
        
        fraseCard.innerHTML = 'Ele é perfeito'
        frase.innerHTML =''
        setTimeout(function(){
            frase.innerHTML = `
            Parabéns vocês nasceram um para o outro, esse Pet atende todos 
            aqueles requisitos que você filtrou...
            
            <a href="#" onclick="trocaDeItem()">Oque acontece agora ? </a>
            `
        },2000)       
    })

    deslike.addEventListener('click', function(){
        card.classList.add('rotate')
        setTimeout(function(){
            card.classList.remove('rotate')
            if(quantidade == petLista.length ){
                quantidade = 0
            }
            itemCard.innerHTML = petLista[quantidade]
            quantidade++
            console.log(petLista.length)

           

        },300)
    })
}