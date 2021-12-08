CREATE TABLE IF NOT EXISTS user_permission
(
    id_user                       int,
    id_permission                 int,
    fk_user_permission_permission int,
    CONSTRAINT fk_user_permission FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES permission (id)
);