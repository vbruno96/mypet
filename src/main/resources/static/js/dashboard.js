let links = document.querySelectorAll('#dashLink')
let icon = document.querySelectorAll('.select-filtro')


links.forEach(function(elemento, index){
    elemento.addEventListener('mouseover', function(){
       icon[index].classList.add('hoverIcon')
    })
})

links.forEach(function(elemento, index){
    elemento.addEventListener('mouseout', function(){
       icon[index].classList.remove('hoverIcon')
    })
})

