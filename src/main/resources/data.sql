-- STUDENT
INSERT INTO tb_users (
    id,
    username,
    registration,
    name,
    email,
    password,
    lattes_url,
    description
) VALUES (
    1,
    'student',
    '20260001',
    'Student Test',
    'student@test.com',
    '$2a$10$z5D2hhCvE5azLSNCtF055e9sFJr24gERdOKbpkyOy.PEaUgnf5n9C',
    NULL,
    'Usuário de teste do tipo Student'
);

INSERT INTO tb_students (
    id,
    course,
    current_period,
    ira,
    receive_notifications
) VALUES (
    1,
    'CIENCIA_DA_COMPUTACAO',
    5,
    8.5,
    TRUE
);


-- PROFESSOR
INSERT INTO tb_users (
    id,
    username,
    registration,
    name,
    email,
    password,
    lattes_url,
    description
) VALUES (
    2,
    'professor',
    '20260002',
    'Professor Test',
    'professor@test.com',
    '$2a$10$1bZQBv.FJsIi5Ny.JmzsDObSSw30g.b4piRYkAYofVJ2fPPpmsViG',
    NULL,
    'Usuário de teste do tipo Professor'
);

INSERT INTO tb_professors (
    id,
    department
) VALUES (
    2,
    'COMPUTACAO'
);


-- PROJECT
WITH new_project AS (
    INSERT INTO projects (
        title,
        start_date,
        end_date,
        status
    )
    VALUES (
        'Projeto de Inteligência Artificial',
        CURRENT_DATE,
        NULL,
        'ACTIVE'
    )
    RETURNING id
)
INSERT INTO project_advisors (
    project_id,
    professor_id
)
SELECT
    id,
    2
FROM new_project;


-- ADJUST SEQUENCES
SELECT setval(
    pg_get_serial_sequence('tb_users', 'id'),
    COALESCE((SELECT MAX(id) FROM tb_users), 1)
);

SELECT setval(
    pg_get_serial_sequence('tb_students', 'id'),
    COALESCE((SELECT MAX(id) FROM tb_students), 1)
);

SELECT setval(
    pg_get_serial_sequence('tb_professors', 'id'),
    COALESCE((SELECT MAX(id) FROM tb_professors), 1)
);
