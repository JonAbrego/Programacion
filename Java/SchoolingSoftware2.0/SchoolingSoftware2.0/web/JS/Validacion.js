/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validar(f) {
    if (f.pass.value  != f.cPass.value) {
        alert ('Las Contraseñas no coinciden'); 
        f.pass.focus();
        return false;
    }
    return true;
}

