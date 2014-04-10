/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.filter.ComboboxFilter', {
    extend: 'sunerp.component.core.BaseGirdFilter',
    initComponent: function () {
        var me = this;
        me.comp.on('change', me.onChange, me);
        me.callParent(arguments);
    },
    onChange: function (comp, newValue, oldValue, eOpts) {
        this.filter.setValue(sunerp.Utils.toString(newValue));
        this.store.loadPage(1);
    }
});