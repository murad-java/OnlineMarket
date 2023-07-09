import Toastify from 'toastify-js';
import 'toastify-js/src/toastify.css';


class Toasty {
    showError(msg) {
        Toastify({
            text: msg,
            duration: 3000, // время скрытия уведомления в миллисекундах
            backgroundColor: 'rgba(154,11,11,0.91)', // цвет фона уведомления
        }).showToast()
    }

    showInfo(msg) {
        Toastify({
            text: msg,
            duration: 3000, // время скрытия уведомления в миллисекундах
            backgroundColor: 'rgba(137,225,21,0.89)', // цвет фона уведомления
            className: "custom-toastify-text"
        }).showToast();
    }
}

export default new Toasty