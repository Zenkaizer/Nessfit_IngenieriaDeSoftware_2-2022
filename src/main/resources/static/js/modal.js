var inputs = document.getElementsByTagName("input");
for (i = 0; i < inputs.length; i++) {
    var input = inputs[i];
    input.addEventListener("keypress", function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            document.getElementById("modalButton").click();
        }
    })
}