let btn = document.querySelector('button.search-icon');
let close = document.querySelector('button.close');
btn.addEventListener('click', clicar);
close.addEventListener('click', fechar)

let inpth = document.querySelector('input#header-changer')
let inpts = document.querySelector('input#sidebar-changer')
function clicar(){
	btn.addEventListener('click', mudar);
}
function mudar(){
	btn.type = 'submit';
}
function fechar(){
	btn.removeEventListener('click', mudar);
	btn.type = 'button';
}


let h1 = document.querySelector('div#h1');
h1.addEventListener('click', h1change);
let h2 = document.querySelector('div#h2');
h2.addEventListener('click', h2change);
let h3 = document.querySelector('div#h3');
h3.addEventListener('click', h3change);
let h4 = document.querySelector('div#h4');
h4.addEventListener('click', h4change);
let h5 = document.querySelector('div#h5');
h5.addEventListener('click', h5change);
let h6 = document.querySelector('div#h6');
h6.addEventListener('click', h6change);
let h7 = document.querySelector('div#h7');
h7.addEventListener('click', h7change);
let h8 = document.querySelector('div#h8');
h8.addEventListener('click', h8change);
let h9 = document.querySelector('div#h9');
h9.addEventListener('click', h9change);
let h10 = document.querySelector('div#h10');
h10.addEventListener('click', h10change);
let h11 = document.querySelector('div#h11');
h11.addEventListener('click', h11change);
let h12 = document.querySelector('div#h12');
h12.addEventListener('click', h12change);
let h13 = document.querySelector('div#h13');
h13.addEventListener('click', h13change);
let hDef = document.querySelector('button#h-def');
hDef.addEventListener('click', hDefault);


function h1change(){
	inpth.value = 'app-header header-shadow bg-vicious-stance header-text-light';
}
function h2change(){
	inpth.value = 'app-header header-shadow bg-slick-carbon header-text-light';
}
function h3change(){
	inpth.value = 'app-header header-shadow bg-asteroid header-text-light';
}
function h4change(){
	inpth.value = 'app-header header-shadow bg-warm-flame header-text-dark';
}
function h5change(){
	inpth.value = 'app-header header-shadow bg-night-fade header-text-dark';
}
function h6change(){
	inpth.value = 'app-header header-shadow bg-sunny-morning header-text-dark';
}
function h7change(){
	inpth.value = 'app-header header-shadow bg-tempting-azure header-text-dark';
}
function h8change(){
	inpth.value = 'app-header header-shadow bg-heavy-rain header-text-dark';
}
function h9change(){
	inpth.value = 'app-header header-shadow bg-mean-fruit header-text-dark';
}
function h10change(){
	inpth.value = 'app-header header-shadow bg-deep-blue header-text-dark';
}
function h11change(){
	inpth.value = 'app-header header-shadow bg-plum-plate header-text-light';
}
function h12change(){
	inpth.value = 'app-header header-shadow bg-happy-fisher header-text-dark';
}
function h13change(){
	inpth.value = 'app-header header-shadow bg-happy-green header-text-light';
}
function hDefault(){
	inpth.value = 'app-header header-shadow';
}

let s1 = document.querySelector('div#s1');
s1.addEventListener('click', s1change);
let s2 = document.querySelector('div#s2');
s2.addEventListener('click', s2change);
let s3 = document.querySelector('div#s3');
s3.addEventListener('click', s3change);
let s4 = document.querySelector('div#s4');
s4.addEventListener('click', s4change);
let s5 = document.querySelector('div#s5');
s5.addEventListener('click', s5change);
let s6 = document.querySelector('div#s6');
s6.addEventListener('click', s6change);
let s7 = document.querySelector('div#s7');
s7.addEventListener('click', s7change);
let s8 = document.querySelector('div#s8');
s8.addEventListener('click', s8change);
let s9 = document.querySelector('div#s9');
s9.addEventListener('click', s9change);
let s10 = document.querySelector('div#s10');
s10.addEventListener('click', s10change);
let s11 = document.querySelector('div#s11');
s11.addEventListener('click', s11change);
let s12 = document.querySelector('div#s12');
s12.addEventListener('click', s12change);
let s13 = document.querySelector('div#s13');
s13.addEventListener('click', s13change);
let sDef = document.querySelector('button#s-def');
sDef.addEventListener('click', sDefault);


function s1change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-vicious-stance sidebar-text-light';
}
function s2change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-slick-carbon sidebar-text-light';
}
function s3change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-asteroid sidebar-text-light';
}
function s4change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-warm-flame sidebar-text-dark';
}
function s5change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-night-fade sidebar-text-dark';
}
function s6change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-sunny-morning sidebar-text-dark';
}
function s7change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-tempting-azure sidebar-text-dark';
}
function s8change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-heavy-rain sidebar-text-dark';
}
function s9change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-mean-fruit sidebar-text-dark';
}
function s10change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-deep-blue sidebar-text-dark';
}
function s11change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-plum-plate sidebar-text-light';
}
function s12change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-happy-fisher sidebar-text-dark';
}
function s13change(){
	inpts.value = 'app-sidebar sidebar-shadow bg-happy-green sidebar-text-light';
}
function sDefault(){
	inpts.value = 'app-sidebar sidebar-shadow';
}



