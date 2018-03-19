var seckill={
    URL : {
        now : function () {
            return "/seckill/time/now"
        }
    },
    detail: {
        //详情页初始化
        inition: function (param) {
            var endTime = param['endTime'];
            var startTime=param['startTime'];

            $.get(seckill.URL.now(),{},function(result){
                var time=result['data'];

                if (result && result['success']){

                    if (time > endTime){

                        var killPhoneModal = $('#killPhoneModal');
                        killPhoneModal.modal({
                            show: true,//显示弹出层
                            backdrop: 'static',//禁止位置关闭
                            keyboard: false//关闭键盘事件
                        });

                    }else if (time < startTime){

                        seckill.countDown(startTime);
                    }
                }else{
                    alert("false");
                }
            });
        }
    },
    countDown : function (startTime) {
        var seckillBox = $('#seckill-box');
        seckillBox.countdown(startTime,function(event){
            var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒 ');
            seckillBox.html(format);
        }).on('finish.countdown', function () {

        });
    }
}