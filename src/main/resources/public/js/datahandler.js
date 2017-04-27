/**
 * Created by davidkulchar on 2017.04.27..
 */
$(document).ready(function (){


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

    var renderMainContent = function() {
        var start = '/get_products';
    };

    var createFilledTag = function(tag, cass, content) {
        var div = document.createElement(tag);
        div.className = cass;
        var divContent = document.createTextNode(content);
        div.appendChild(divContent);
        return div
    };

    var renderProduct = function(product) {
        console.log(product);
        var headDiv = document.createElement('figure');
        var img = document.createElement('img');
        var caption = document.createElement('div');
        var h4 = createFilledTag('h4', "group inner list-group-item-heading", product.name );
        var p = createFilledTag('p', "group inner list-group-item-text", product.description);
        var payDiv = document.createElement('div');
        var currencyDiv = createFilledTag('div', 'lead', product.priceTag);
        var currencyContainer = createFilledTag('div', 'col-xs-12 col-md-6', currencyDiv.innerHTML);
        var buttonDiv = createFilledTag('a', 'add_to_cart_btn', "Add to cart");
        var buttonContainer = createFilledTag('div', 'lead', buttonDiv.innerHTML);
        payDiv.className = 'row';
        payDiv.appendChild(currencyContainer);
        payDiv.appendChild(buttonContainer);
        img.setAttribute('src', ("img/" + product.picture.toString()));
        img.setAttribute('width', '100%');
        caption.className = 'caption';
        caption.appendChild(h4);
        caption.appendChild(p);
        payDiv.className = 'row';
        caption.appendChild(payDiv);
        headDiv.appendChild(img);
        headDiv.appendChild(caption);
        headDiv.className = 'thumbnail';
        document.getElementById('columns').appendChild(headDiv);
    };


    var getData = function() {
        $.ajax({
            type: 'GET',
            url: "/get_products",
            success: function (data) {
                data = JSON.parse(data);
                for (var product in data) {
                    renderProduct(data[product]);
                }
            }
        });
    };

    getData();
});