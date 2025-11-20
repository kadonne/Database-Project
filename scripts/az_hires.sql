\echo '\nThis script aims to help determine which person is suitable for a job.\nIt also provides the self claimed courses, which are needed to match the course transcript inputs.\nPerson with required skills - fulfilled skills difference of <=2 are qualified for the job.\n'
\echo '\nNOTE:Run this script after you choose the per_id from the DatabaseRunner.java\nIt needs to pull the information of the person from LD to AZ first.'
\prompt '\nEnter per_id: ' per_id
\echo '\nSelf claimed courses: '
SELECT DISTINCT c_code FROM has_skill NATURAL JOIN teaches WHERE per_id = :per_id ORDER BY c_code;
\echo '\nPotentially successful job offers with number of skills fulfilled: '
WITH requires_skill_count AS (
    SELECT job_code, COUNT(sk_code) AS skill_count
    FROM requires NATURAL JOIN job
    GROUP BY job_code
)
SELECT 
    r.job_code,
    COUNT(h.sk_code) AS fulfilled_skill_count,
    r.skill_count AS required_skill_count
FROM requires_skill_count AS r
NATURAL JOIN has_skill AS h NATURAL JOIN requires NATURAL JOIN job
WHERE h.per_id = :per_id
GROUP BY r.job_code, r.skill_count
ORDER BY r.job_code;

