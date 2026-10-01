-- =========================================================
-- STUDENT
-- =========================================================

INSERT INTO tb_users (
    id,
    username,
    registration,
    name,
    email,
    password,
    lattes_url,
    description
)
VALUES (
    1,
    'student',
    '20260001',
    'Student Test',
    'student@test.com',
    '$2a$10$z5D2hhCvE5azLSNCtF055e9sFJr24gERdOKbpkyOy.PEaUgnf5n9C',
    NULL,
    'Usuário de teste do tipo Student'
)
ON CONFLICT DO NOTHING;


INSERT INTO tb_students (
    id,
    course,
    current_period,
    ira,
    receive_notifications
)
VALUES (
    1,
    'CIENCIA_DA_COMPUTACAO',
    5,
    8.5,
    TRUE
)
ON CONFLICT DO NOTHING;


-- =========================================================
-- PROFESSOR
-- =========================================================

INSERT INTO tb_users (
    id,
    username,
    registration,
    name,
    email,
    password,
    lattes_url,
    description
)
VALUES (
    2,
    'professor',
    '20260002',
    'Professor Test',
    'professor@test.com',
    '$2a$10$1bZQBv.FJsIi5Ny.JmzsDObSSw30g.b4piRYkAYofVJ2fPPpmsViG',
    NULL,
    'Usuário de teste do tipo Professor'
)
ON CONFLICT DO NOTHING;


INSERT INTO tb_professors (
    id,
    department
)
VALUES (
    2,
    'COMPUTACAO'
)
ON CONFLICT DO NOTHING;


-- =========================================================
-- PROJECT
-- =========================================================

WITH new_project AS (
    INSERT INTO projects (
        title,
        start_date,
        end_date,
        status
    )
    VALUES (
        'Projeto de Inteligência Artificial',
        DATE '2026-10-01',
        NULL,
        'ACTIVE'
    )
    ON CONFLICT (title, start_date)
    DO UPDATE SET title = EXCLUDED.title
    RETURNING id
)
INSERT INTO project_advisors (
    project_id,
    professor_id
)
SELECT
    id,
    2
FROM new_project
ON CONFLICT DO NOTHING;


-- =========================================================
-- ADJUST SEQUENCE
-- =========================================================

SELECT setval(
    pg_get_serial_sequence('tb_users', 'id'),
    COALESCE((SELECT MAX(id) FROM tb_users), 1)
);
