//ACTUAL CODE
//ACTUAL CODE
var trainValue;
var URL = "http://localhost:8080/admin/";
$(document).ready(function () {
    sessionStorage["charTrain"]=null;
    $.ajax({
        url: URL + 'data',
        success: function (response) {
            var table = $('#allData').DataTable({
                data: response,
                columns: [
                    { "data": "charId" },
                    { "data": "name" },
                    { "data": "createdDateTime" },
                    { "data": "noOfTimesPlayed" },
                    { "data": "lastPlayedDateTime" }
                ],
            });
            $('#allData tbody').on('click', 'tr', function () {
                $(this).toggleClass('active');
            });

            $('#rowsel').click(function () {

                var idselected = table.rows('.active').data();
                var temp = idselected[0];
                trainValue = temp.charId;
               // console.log(trainValue);
                sessionStorage["charTrain"]=trainValue;
                //rIds Data Population
            //   callTrain();
            });
        }
    });
});

var callTrain = function(){
    reTrain();
}


var reTrain = function () {
    if(sessionStorage["charTrain"]!==null||sessionStorage["charTrain"]!==undefined)
   location.href = "/adminchartrain.html";
    //  location.reload();
};

var redirectToQues = function () {
    location.href = "/adminques.html";
};

var redirectToChar = function () {
    location.href = "/adminchar.html";
}