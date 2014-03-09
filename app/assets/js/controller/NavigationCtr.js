/**
 * Created by dungvn3000 on 2/11/14.
 */

Ext.define('sunerp.controller.NavigationCtr', {
    extend: 'Deft.mvc.ViewController',
    control: {
        navigation: {
            selector: 'navigation',
            listeners: {
                selectionchange: 'onNavSelectionChange'
            }
        },
        contentPanel: true
    },

    init: function () {
        this.callParent(arguments);
    },

    onNavSelectionChange: function (selModel, records) {
        var record = records[0],
            text = record.get('text'),
            id = record.get('id'),
            viewClassName = record.raw.view,
            contentPanel = this.getContentPanel();

        if (id) {// only leaf nodes have ids
            contentPanel.removeAll(true);
            //create new view
            var cmp = Ext.create(viewClassName);
            contentPanel.add(cmp);
            contentPanel.setTitle(text);
        }
    }

});