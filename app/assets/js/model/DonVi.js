/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.DonVi', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'name',
        'companyId',
        'company',
        'khoiDonViId',
        'khoiDonVi'
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.Company', name: 'company'},
        {type: 'belongsTo', model: 'sunerp.model.KhoiDonVi', name: 'khoiDonVi'}
    ]
});