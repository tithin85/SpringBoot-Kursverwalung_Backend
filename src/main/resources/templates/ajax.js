

$('#SubmitButton').click(function (){


    $.ajax({

        type:'POST',
        contentType:'application/json;charset=utf-8',
        url:"/kurs/add",
        data:JSON.stringify({
            name: $('#name').val(),
            anzahlTage: $('#anzahlTage').val(),
            wieOftinWoche: $('#wieOftinWoche').val(),
            startDatum: $('#startDatum').val(),
            minTnZahl: $('#minTnZahl').val(),
            maxTnZahl: $('#maxTnZahl').val(),
            gebuehrBrutto:$('#gebuehrBrutto').val(),
            mwstProzent:$('#mwstProzent').val(),
            kursBeschreibung:$('#kursBeschreibung').val(),
            status:$('#status').val(),


        }),


        success:function(data){
            console.log(data);

        },

        error:function (err){
            console.log(err);
        }


    })
})
