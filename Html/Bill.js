const enum_url="http://localhost:8080/getEnums";
const addItem_url = "http://localhost:8080/add";
const finalBill_url = "http://localhost:8080/finalize";

var salestaxList = [];
var totalCostList = [];

getEnums();
async function getEnums(){
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  
let obj = await fetch(enum_url, requestOptions).then(response => response.json()).then(data => data.responseObject);
console.log(obj)
const element = document.getElementById("Category");
for(let i =0; i < obj.length;i++){
  var newName = document.createElement("option");
  newName.innerHTML=obj[i];
  element.append(newName);
}
}


async function addAnother(){
  var raw ={
    pricePerUnit: document.getElementById("pricePerUnit").value,
    quantity: document.getElementById("quantity").value,
    isItemImported: document.getElementById("locale").value,
    category: document.getElementById("Category").value,
    itemName: document.getElementById("item-name").value
}

document.getElementById("pricePerUnit").id = 'deprecate'
document.getElementById("quantity").id = 'deprecate'
document.getElementById("locale").id = 'deprecate'

document.getElementById("item-name").id = 'deprecate'

raw = JSON.stringify(raw);

const elementDel = document.getElementById("Category");
elementDel.id = "deprecate";

var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};


let costResponses = await fetch(addItem_url,requestOptions).then(response => response.json()).then(data => data.responseObject);
console.log(costResponses.item)

const costelement = document.getElementById("cost-price");
const taxElement = document.getElementById("tax-price");
const totalelement = document.getElementById("total-price");
costelement.innerHTML = costResponses.item[0];
taxElement.innerHTML = costResponses.item[1];
totalelement.innerHTML = costResponses.item[2];

salestaxList.push(costResponses.item[1]);
totalCostList.push(costResponses.item[2]);

costelement.id = 'deprecate';
taxElement.id = 'deprecate';
totalelement.id = 'deprecate';



  const element = document.getElementById("item-adder");
  const newDiv = document.createElement("tr");
  newDiv.innerHTML = `<td>
  <br>
  <input id="item-name" placeholder="Name" class="form-control">
</td>
<td>
  <br>
  <input id="quantity" type="number" placeholder="Quantity" class="form-control">
</td>
<td>
  
  <br>
  <input id="pricePerUnit" placeholder="Price per Unit" class="form-control">
</td>
<td>
  <br>
  <select class="form-control" id="locale">
   <option selected>DOMESTIC</option>
   <option>IMPORTED</option>
   </select>
</td>
<td>
  <br>
 <select class="form-control" id="Category">
  </select>
</td>
<td>
  <div>
      <br>
      <h6 id="cost-price"></h6>
  </div>
</td>
<td>
  <div>
      <br>
      <h6 id="tax-price"></h6>
  </div>
</td>
<td>
  <div>
      <br>
      <h6 id="total-price"></h6>
  </div>
</td>`

element.append(newDiv)
getEnums()
}




async function getTotal(){
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = {
    salesTaxComponent: salestaxList,
    costComponent:totalCostList
}
  
  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: JSON.stringify(raw),
    redirect: 'follow'
  };
  
  
  let finalResponse = await fetch(finalBill_url,requestOptions).then(response => response.json()).then(data => data.responseObject);
  const element = document.getElementById("item-adder");
  const newDiv = document.createElement("tr");
  newDiv.innerHTML = `<td>
  <br>
</td>
<td>
  <br>
</td>
<td>
  <br>
</td>
<td>
  <br>
</td>
<td>
  <br>
</td>
<td>
  <div>
      <br>
      <h6>Total Amount</h4>
  </div>
</td>
<td>
  <div>
      <br>
      <h6 id="tax"></h6>
  </div>
</td>
<td>
  <div>
      <br>
      <h6 id="totalCost"></h6>
  </div>
</td>`

element.append(newDiv)
document.getElementById("tax").innerHTML = finalResponse[0];
document.getElementById("totalCost").innerHTML = finalResponse[1];


}