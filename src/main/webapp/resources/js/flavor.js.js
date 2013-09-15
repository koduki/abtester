$(function() {
    var eachAll = function(item, callback) {
        if (item.children().size() === 0) {
            callback(item);
        } else {
            item.children().each(function(i, x) {
                eachAll($(x), callback);
            });
        }
    };

    var scripts = {
        toString: function() {
            var str = _.reduce(scripts, function(r, x) {
                var src = (typeof(x) !== "function") ? x : "";
                return r + src;
            }, "");
            return str;
        }
    };
    window.scripts = scripts;

    eachAll($("#editor-block"), function(x) {
        var item = $(x);
        item.click(function() {
            var name = item.attr("id");
            var text = item.text();
            console.log(name)
            $("#flavor-name .value").text(name);
            $("#flavor-value .value").val(text);
        });
    });

    $("#apply").click(function() {
        var selected = $("#flavor-name .value").text();
        var item = '$("#' + selected + '")';
        var value = $("#flavor-value .value").val();
        var src = item + '.text("' + value + '");';
        var key = item + '.text';
        console.log(src);
        scripts[key] = src;
        var target = $(document.getElementById("frm_update:frm_update:scritps"));
        //target.val(scripts.toString());
        target.val(target.val() + src);

        eval(src);
    });

    // apply flavor html.
    eval($(document.getElementById("frm_update:frm_update:scritps")).val());
});