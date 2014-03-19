/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.KhoiDonVi', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'name',
        'companyId',
        'company',
        {name: 'company.name', mapping: 'company.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.Company', name: 'company'}
    ]
});
