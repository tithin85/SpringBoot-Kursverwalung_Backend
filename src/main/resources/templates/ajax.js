

$('#SubmitButton').click(function (){

    $.ajax({

        type:'POST',
        contentType:'application/json;charset=utf-8',
        url:"http://localhost:8080/kurs/add",
        data:JSON.stringify({
            name: $('#name').val(),
            anzahlTage: $('#anzahlTage').val(),
            wieOftinWoch: $('#wieOftinWoch').val(),
            startDatum: $('#startDatum').val(),
            minTnZahl: $('#minTnZahl').val(),
            maxTnZahl: $('#maxTnZahl').val(),
            gebuehrBrutto:$('#gebuehrBrutto').val(),
            mwstProzent:$('#mwstProzent').val(),
            kursBeschreibung:$('#kursBeschreibung').val(),
            status:$('#status').val(),


        }),


        success:function(){
            console.log("succes");

        },

        error:function (error){
            console.log("error");
        }


    })
})
