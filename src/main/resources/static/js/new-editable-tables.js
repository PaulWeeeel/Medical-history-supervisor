var EditableTable = function () {

    return {

        //main function to initiate the module
        init: function (turl) {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow, isNew) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                var i = 0;
                for (var iLen = jqTds.length; i < iLen - 2; i++) {
                	jqTds[i].innerHTML = '<input type="text" class="form-control small" value="' + aData[i] + '">';
                }

                if (isNew) {
                    jqTds[i++].innerHTML = '<a class="save" data-mode="new" href="">保存</a>';
                    jqTds[i++].innerHTML = '<a class="cancel" data-mode="new" href="">取消</a>';
                } else {
                    jqTds[i++].innerHTML = '<a class="save" href="">保存</a>';
                    jqTds[i++].innerHTML = '<a class="cancel" href="">取消</a>';
                }
            }

            function saveRow(oTable, nRow, url) {
            	var aData = oTable.fnGetData(nRow);
                var jqInputs = $('input', nRow);

                var i = 0;
                for (var iLen = jqInputs.length; i < iLen; i++) {
                	oTable.fnUpdate(jqInputs[i].value, nRow, i, false);
                }

                oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, i++, false);
                oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, i++, false);
                oTable.fnDraw();
            }

            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records per page",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
                e.preventDefault();
                var mydata=new Array();
                var num = $('th', oTable).length;

                var i = 0;
                for (; i < num - 2; i++) {
                	mydata[i] = '';
                }
                mydata[i++] = '<a class="save" data-mode="new" href="">保存</a>';
                mydata[i++] = '<a class="cancel" data-mode="new" href="">取消</a>';

                var aiNew = oTable.fnAddData(mydata);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow, true);
                nEditing = nRow;
            });

            $('#editable-sample a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("你确定要删除这条记录吗 ?") == false) {
                    return;
                }

                var nRow = $(this).parents('tr')[0];
                // alert("Deleted! Do not forget to do some ajax to sync with backend :)");
                $.ajax({
                    type: "post",
                    url: turl + '/delete?id=' + oTable.fnGetData(nRow)[0],
                    data: {},
                    success: function () {
                        alert("delete success!");
                    }
                });
                oTable.fnDeleteRow(nRow);
            });

            $('#editable-sample a.cancel').live('click', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#editable-sample a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow, false);
                    nEditing = nRow;
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow, false);
                    nEditing = nRow;
                }
            });

            $('#editable-sample a.save').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];
                var aData = $('input', nRow);
                
                var myData = null;

                if (turl == "/stock") {
                    myData = {
                        "id" : aData[0].value,
                        "medicine" : aData[1].value,
                        "stock" : aData[2].value,
                        "unitPrice" : aData[3].value,
                        "cost" : aData[4].value
                    }
                } else if (turl == "/record") {
                    myData = {
                        "id" : aData[0].value,
                        "type" : aData[1].value,
                        "number" : aData[2].value,
                        "dateTime" : aData[3].value
                    }
                }
                else if (turl == "/disease") {
                    myData = {
                        // "id" : aData[0].value,
                        "disease" : aData[0].value,
                        "level" : aData[1].value,
                        "riskDegree" : aData[2].value
                    }
                }else if (turl == "/case-history") {
                    saveRow(oTable, nEditing, turl);
                    nEditing = null;
                    return;
                }

                if ($(this).attr("data-mode") == "new") {
                	/* A new stock to be added */
                    $.ajax({
                    	type: "post",
                    	url: turl + '/add',
                    	contentType:"application/json;charset=utf-8",
                        data: JSON.stringify(myData),
                    	success: function () {
                    	    alert("upload success!");
                        }
                	});
                } else {
                    /* A exist data to be updated  */
                    $.ajax({
                    	type: "post",
                    	url: turl + '/edit',
                    	contentType:"application/json;charset=utf-8",
                    	data: JSON.stringify(myData),
                    	success: function () {
                    	    alert("edit success!");
                        }
                	});
                }

                saveRow(oTable, nEditing, turl);
                nEditing = null;
            });
        }

    };

}();