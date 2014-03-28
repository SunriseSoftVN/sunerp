/**
 * Created by dungvn3000 on 3/14/14.
 * The model automatic update mapping field when set associations table.
 */

Ext.define('sunerp.model.BaseModel', {
    extend: 'Ext.data.Model',
    set: function (fieldName, newValue) {
        var me = this;
        me.callParent(arguments);
        if (newValue != null && Ext.isObject(newValue)) {
            Ext.each(me.associations.keys, function (table) {
                if (fieldName == table) {
                    for (var key in newValue) {
                        me.set(table + "." + key, newValue[key]);
                    }
                    me.set(table + 'Id', newValue.id);
                }
            });
        }
    }
});