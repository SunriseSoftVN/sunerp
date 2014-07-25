Ext.define('sunerp.view.sophancong.KhoaSoPhanCongView', {
    extend: 'Ext.panel.Panel',
    controller: 'sunerp.controller.sophancong.KhoaSoPhanCongCtr',
    requires: [
        'sunerp.component.MonthCb'
    ],
    items: [
        {
            xtype: 'form',
            bodyPadding: 5,
            items: [
                {
                    xtype: 'monthcb'
                },
                {
                    xtype: 'button',
                    text: 'Khóa',
                    action: 'lock'
                },
                {
                    xtype: 'button',
                    text: 'Mở khóa',
                    action: 'unlock'
                }
            ]
        }
    ]
});