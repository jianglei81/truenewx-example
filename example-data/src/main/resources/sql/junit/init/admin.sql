insert into T_MANAGER (id, username, password, fullname, top) values
(1, 'admin', '870a4a03c42c2af445f98d2746bfba47a7f01cb1c843a12a5136e9720a53fada', '顶级管理员', 1),
(2, 'normal1', '5a51ff8e3a7b005dbbc907dbea7d12f0ae8d65a1aafe283999ac764ef504cb1e', '普通管理员1', 0),
(3, 'general1', '5a5f7e6e3c850050bb490cdbee6e8224a482c7710ffe78399ca8764ef560c6f0', '一般管理员1', 0),
(4, 'cs1', '5a5bf82e32960096bbf907dbea3bb247a080cfd1defef83991a9764ef5fac23f', '客服MM1', 0);

insert into T_ROLE (id, name, ordinal, permission_string) values
(1, '用户管理者', 1, '["USER_LIST","USER_DISABLE","USER_DETAIL"]'),
(2, '客服', 2, '');

insert into T_MANAGER_R_ROLE (manager_id, role_id) values
(2, 1),
(4, 2);
