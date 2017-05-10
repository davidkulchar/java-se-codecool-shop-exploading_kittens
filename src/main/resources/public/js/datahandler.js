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

    var createFilledTag = function(tag, cass, content) {
        var div = document.createElement(tag);
        div.className = cass;
        var divContent = document.createTextNode(content);
        div.appendChild(divContent);
        return div
    };

    var getCart = function (data) {
        document.getElementById("cart").innerHTML = "";
        document.getElementById("cart").appendChild(renderThead());
        for (var item in data.items) {
            var tr = document.createElement('tr');
            var thName = getTableElement('td', data.items[item].name);
            var thPrice = getTableElement('td', data.items[item].priceTag);
            var thQuantity = getTableElement('td', data.items[item].quantity);
            var thDelete = document.createElement("Button");
            thDelete.appendChild(document.createTextNode("Delete"));
            thDelete.className = "btn btn-danger btn-xs";
            thDelete.setAttribute("style", "float:right");
            thDelete.onclick = function() {deleteProductFromCart(data.items[item].id)};
            tr.appendChild(thName);
            tr.appendChild(thPrice);
            tr.appendChild(thQuantity);
            tr.appendChild(thDelete);
            document.getElementById("cart").appendChild(tr);
        }
        var tr = renderTableEnd(data);
        document.getElementById("cart").appendChild(tr);
    };

    var renderTableEnd = function(data) {
        var tr = document.createElement('tr');
        var thName = getTableElement('th', 'Total Price :');
        var thPrice = getTableElement('th', data.fullPrice + " HUF");
        var thQuantity = getTableElement('th', 'Total Quantity');
        var thAction = getTableElement('th', data.quantity);
        tr.appendChild(thName);
        tr.appendChild(thPrice);
        tr.appendChild(thQuantity);
        tr.appendChild(thAction);
        return tr;

    };

    var getTableElement = function (tag, text) {
        var nTag = document.createElement(tag);
        nTag.appendChild(document.createTextNode(text));
        return nTag;
    };

    var renderThead = function () {
        var thead = document.createElement('thead');
        var thName = getTableElement('th', 'Name');
        var thPrice = getTableElement('th', 'Price');
        var thQuantity = getTableElement('th', 'Quantity');
        var thDelete = getTableElement('th', '');
        thead.appendChild(thName);
        thead.appendChild(thPrice);
        thead.appendChild(thQuantity);
        thead.appendChild(thDelete);
        return thead;
    };

    var fillCart = function() {
        $.ajax({
            type: 'GET',
            url: ("/get_cart"),
            success: function (data) {
                data = JSON.parse(data);
                getCart(data);
                return data;
            }
        });
    };

    var reCountItems = function () {
        $.ajax({
            type: 'GET',
            url: ("/cartcount"),
            success: function (data) {
                data = JSON.parse(data);
                document.getElementById("counter").innerHTML = data.quantity;
                return data;
            }
        });
    };

    var addToCart = function (id) {
        $.ajax({
            type: 'GET',
            url: ("/addToCart/"+id),
            success: function (data) {
                reCountItems();
                fillCart();
                return data;
            }
        });
    };

    var deleteProductFromCart = function (id) {
        $.ajax({
            type: 'GET',
            url: ("/remove_from_cart/"+id),
            success: function (data) {
                reCountItems();
                fillCart();
                return data;

            }
        });
    };

    var renderProduct = function(product) {
        var headDiv = document.createElement('figure');
        var img = document.createElement('img');
        var caption = document.createElement('div');
        var h4 = createFilledTag('h4', "group inner list-group-item-heading", product.name );
        var p = createFilledTag('p', "group inner list-group-item-text", product.description);
        var payDiv = document.createElement('div');
        var currencyDiv = createFilledTag('div', 'lead', product.priceTag);
        var currencyContainer = createFilledTag('div', 'col-xs-12 col-md-6', currencyDiv.innerHTML);
        var buttonContainer = createFilledTag('button', 'col-xs-12 col-md-6 add_to_cart_btn', "Add to cart");
        buttonContainer.onclick =  function() {addToCart(product.id)};
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


    var getData = function(theurl) {
        $.ajax({
            type: 'GET',
            url: theurl,
            success: function (data) {
                document.getElementById("columns").innerHTML = "";
                data = JSON.parse(data);
                for (var product in data) {
                    renderProduct(data[product]);
                }
            }
        });
    };
    
    var renderMenuPoint = function (data, url) {
        var a = document.createElement('a');
        var text = document.createTextNode(data.name);
        a.className = "col-md-3 my_cart";
        a.onclick = function () {
            getData(url + "/" +data.name)};
        a.appendChild(text);
        document.getElementById("head-r").appendChild(a);
    };

    var getMenu = function(theurl) {
        $.ajax({
            type: 'GET',
            url: theurl,
            success: function (data) {
                data = JSON.parse(data);
                for (var product in data) {
                    renderMenuPoint(data[product], theurl);
                }
            }
        });
    };

    var generateShoppingCart = function () {
        var a = document.createElement('a');
        var text = document.createTextNode("My cart");
        var span = document.createElement('span');
        span.className = "button-badge";
        span.id = "counter";
        span.appendChild(document.createTextNode("0"));
        a.className = "col-md-3 my_cart";
        a.id = "myBtn";
        a.appendChild(text);
        a.appendChild(span);
        document.getElementById("head-r").appendChild(a);
    };
    
    var renderMenuPoint = function (data, url) {
        var a = document.createElement('a');
        var text = document.createTextNode(data.name);
        a.className = "col-md-3 my_cart";
        a.onclick = function () {
            getData(url + "/" +data.name)};
        a.appendChild(text);
        document.getElementById("head-r").appendChild(a);
    };

    var getMenu = function(theurl) {
        $.ajax({
            type: 'GET',
            url: theurl,
            success: function (data) {
                data = JSON.parse(data);
                for (var product in data) {
                    renderMenuPoint(data[product], theurl);
                }
            }
        });
    };

    generateShoppingCart();
    fillCart();
    getMenu("/suppliers");
    getMenu("/categories");
    getData("/get_products");
    reCountItems();
});