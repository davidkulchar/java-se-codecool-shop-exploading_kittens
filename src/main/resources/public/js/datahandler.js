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
        var data = getData(start);
        for (var product in data) {
            renderProduct(product);
        }
    };

    var createFilledTag = function(tag, cssClass, content) {
        var div = document.createElement(tag);
        div.addClass(cssClass);
        var divContent = document.createTextNode(content);
        div.appendChild(divContent);
        return div
    };

    var renderProduct = function(product) {
        var headDiv = document.createElement('figure');
        var img = document.createElement('img');
        var caption = document.createElement('div');
        var h4 = createFilledTag('h4', "group inner list-group-item-heading", product.name )
        var p = createFilledTag('p', "group inner list-group-item-text", product.description);
        var payDiv = document.createElement('div');
        var currencyDiv = createFilledTag('div', 'lead', product.priceTag);
        var currencyContainer = createFilledTag('div', 'col-xs-12 col-md-6', currencyDiv);
        var buttonDiv = createFilledTag('div', 'btn btn-success');
        var buttonContainer = createFilledTag('div', 'lead', buttonDiv);
        payDiv.addClass('row');
        payDiv.appendChild(currencyContainer);
        payDiv.appendChild(buttonContainer);
        img.setAttribute('src', product.pic);
        img.setAttribute('width', '100%');
        caption.addClass('caption');
        caption.appendChild(h4);
        caption.appendChild(p);
        payDiv.addClass('row');
        caption.appendChild(payDiv);
        headDiv.appendChild(img);
        headDiv.appendChild(caption);
        headDiv.addClass('thumbnail');
        document.getElementById('columns').appendChild(headDiv);
    };


    var getData = function(theurl) {
        $.ajax({
            type: 'GET',
            url: theurl,
            success: function (data) {
                return data;
            }
        });
    };

    renderMainContent();
});