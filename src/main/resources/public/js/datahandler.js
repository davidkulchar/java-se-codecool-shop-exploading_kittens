$(document).ready(function (){

    var createProducts = function(product) {

    };

    var createButton = function(btnLink, buttonText) {
        var prevButton = document.createElement('button');
        var btnText = document.createTextNode(buttonText);
        prevButton.appendChild(btnText);
        prevButton.setAttribute('type', 'button');
        prevButton.className = "btn btn-primary btn-sm";
        prevButton.addEventListener('click', function () {renderPage(btnLink)}, false);
        return prevButton
    };

    var getButtons = function (previous, next) {
        var buttonDiv = document.createElement('div');
        if (previous) {
            buttonDiv.appendChild(createButton(previous, "Previous page"));
        }
        if (next) {
            buttonDiv.appendChild(document.createTextNode(" "));
            buttonDiv.appendChild(createButton(next, "Next page"));
        }
        document.getElementById('next-prev').innerHTML = '';
        document.getElementById('next-prev').appendChild(buttonDiv);
    };

    var renderPage = function(theurl) {
        $.ajax({
            type: 'GET',
            url: theurl,
            success: function (data) {
                return data;
            }
        });
    };

    renderPage(start);
});/**
 * Created by davidkulchar on 2017.04.26..
 */
