/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.core.BaseEditController', {
    extend: 'Deft.mvc.ViewController',
    mainStore: null,
    control: {
        form: {
            selector: 'form'
        },
        saveBtn: {
            selector: 'button[action=save]',
            listeners: {
                click: 'doUpdate'
            }
        }
    },
    init: function () {
        if (this.getView().getModel() != null) {
            this.getForm().loadRecord(this.getView().getModel());
        }
        this.callParent(arguments);
    },
    doUpdate: function () {
        var view = this.getView(),
            form = this.getForm(),
            record = form.getRecord(),
            values = form.getValues(),
            me = this;
        if (form.isValid()) {
            if (record == null) {
                record = new Ext.data.Ext.data.Model();
                //add new record
                me.mainStore.add(record);
            }

            record.set(values);

            form.getForm().getFields().each(function (field) {
                if (field.getXType() == "comboboxx") {
                    record.set(field.getModelName(), field.getSelectedData());
                }
            });

            if (record == null || record.dirty) {
                // synchronize the store after editing the record
                me.mainStore.sync({
                    success: function () {
                        view.close();
                    }
                });
            } else {
                view.close();
            }
        }
    }
});