/**
 * Created by dungvn3000 on 2/11/14.
 */

Ext.define('sunerp.controller.NavigationCtr', {
    extend: 'Ext.app.Controller',

    views: [
        'Navigation'
    ],

    refs: [
        {
            ref: 'viewport',
            selector: 'viewport'
        },
        {
            ref: 'navigation',
            selector: 'navigation'
        },
        {
            ref: 'contentPanel',
            selector: 'contentPanel'
        }
    ],

    init: function () {
        this.control({
            'navigation': {
                selectionchange: this.onNavSelectionChange
            }
        })
    },

    onNavSelectionChange: function (selModel, records) {
        var record = records[0],
            text = record.get('text'),
            id = record.get('id'),
            controllerName = record.raw.controller,
            viewName = "widget." + record.raw.view,
            contentPanel = this.getContentPanel();

        if (id) {// only leaf nodes have ids
            contentPanel.removeAll(true);
            //init controller
            var controller = this.getController(controllerName);

            //create new view
            var className = Ext.ClassManager.getNameByAlias(viewName);
            var ViewClass = Ext.ClassManager.get(className);

            var cmp = new ViewClass();
            contentPanel.add(cmp);
            contentPanel.setTitle(text);
        }
    }

});