let img = document.querySelectorAll('#imgPrevia')
let imgBox = document.querySelectorAll('.img')
let imgSelecioinada = document.querySelector('#imgSelecionada')


img.forEach( (element, index) => {
    element.addEventListener('click', function(){
        imgSelecioinada.src = element.src

        imgBox.forEach((element) => {
            element.classList.remove('selecionada')
        })

        imgBox[index].classList.add('selecionada')
    })
});


let fecharModal = document.querySelector('.fechar-btn')
let modal = document.querySelector('#modal');

fecharModal.addEventListener('click', function(){
    modal.classList.remove('mostrar')
})


function mostrarModal(pet){
    modal.classList.toggle('mostrar');


}