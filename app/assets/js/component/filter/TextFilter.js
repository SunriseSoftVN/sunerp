/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.filter.TextFilter', {
    extend: 'sunerp.component.core.BaseGirdFilter',
    alias: 'widget.textfilter',
    initComponent: function () {
        var me = this;
        me.comp = Ext.create('Ext.form.field.Text', {
            name: 'searchField',
            hideLabel: true,
            emptyText: 'Tìm kiếm...',
            width: 200
        });

        me.comp.on('specialkey', me.onSpecialkey, me);
        me.callParent(arguments);
    },
    onSpecialkey: function (f, e) {
        var me = this;
        var searchValue = f.getValue();
        if (e.getKey() == e.ENTER) {
            me.filter.setValue(searchValue);
            me.store.loadPage(1);
        } else if (e.getKey() == e.ESC) {
            f.setValue("");
            me.filter.setValue(null);
            me.store.loadPage(1);
        }
    }
});