/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

/* Lightweight UI helpers: toast + modal popup */
(function(){
const ensureToastRoot = () => {
let root = document.querySelector('.toast-container');
if (!root) {
root = document.createElement('div');
root.className = 'toast-container';
document.body.appendChild(root);
}
return root;
};


window.showToast = function(message, type = 'success', title = ''){
const root = ensureToastRoot();
const card = document.createElement('div');
card.className = `toast ${type}`;
card.innerHTML = `<div class="toast-title">${title || (type==='success'?'Success':'Notice')}</div><div>${message}</div>`;
root.appendChild(card);
setTimeout(()=>{ card.style.opacity = '0'; card.style.transform = 'translateY(-6px)'; }, 2600);
setTimeout(()=>{ card.remove(); }, 3100);
};


const ensureModal = () => {
let backdrop = document.querySelector('.modal-backdrop');
let modal = document.querySelector('.modal');
if (!backdrop) {
backdrop = document.createElement('div');
backdrop.className = 'modal-backdrop';
document.body.appendChild(backdrop);
}
if (!modal) {
modal = document.createElement('div');
modal.className = 'modal';
modal.innerHTML = `
<div class="modal-card">
<div class="modal-header"></div>
<div class="modal-body"></div>
<div class="modal-actions">
<button class="btn btn-outline" type="button" data-close>Close</button>
</div>
</div>`;
document.body.appendChild(modal);
modal.querySelector('[data-close]').addEventListener('click', hideModal);
backdrop.addEventListener('click', hideModal);
}
return { backdrop, modal };
};


function hideModal(){
const { backdrop, modal } = ensureModal();
backdrop.style.display = 'none';
modal.style.display = 'none';
}


window.showPopup = function(title, message){
const { backdrop, modal } = ensureModal();
modal.querySelector('.modal-header').textContent = title || 'Notification';
modal.querySelector('.modal-body').innerHTML = message || '';
backdrop.style.display = 'block';
modal.style.display = 'grid';
};
})();
