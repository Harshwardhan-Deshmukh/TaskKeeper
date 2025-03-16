-- DUMMY DATA FOR APPLICATION

-- Projects
INSERT INTO projects (name, description) VALUES
('Project Sigma', 'AI-powered automation platform'),
('Project Epsilon', 'Cloud-native analytics solution'),
('Project Zeta', 'Cybersecurity and threat detection system'),
('Project Kappa', 'IoT-based smart city initiative'),
('Project Lambda', 'Blockchain-based financial system'),
('Project Omega', 'Next-gen machine learning algorithms');

-- Teams
INSERT INTO teams (name, description) VALUES
('Automation Team', 'Develops AI-driven automation tools'),
('Cloud Analytics Team', 'Analyzes big data using cloud computing'),
('Cybersecurity Team', 'Enhances security protocols and threat detection'),
('IoT Development Team', 'Builds smart city infrastructure'),
('Blockchain Team', 'Develops secure blockchain applications'),
('ML Research Team', 'Researches and develops machine learning models');

-- Users
INSERT INTO users (name, email, role, project_id, team_id, created_at, updated_at) VALUES
('Mia Taylor', 'mia@example.com', 'DEVELOPER', 1, 1, NOW(), NOW()),
('Noah Smith', 'noah@example.com', 'TESTER', 1, 1, NOW(), NOW()),
('Olivia Johnson', 'olivia@example.com', 'LEADERSHIP', 2, 2, NOW(), NOW()),
('Peter Brown', 'peter@example.com', 'PLATFORM', 2, 2, NOW(), NOW()),
('Quinn Wilson', 'quinn@example.com', 'DEVELOPER', 3, 3, NOW(), NOW()),
('Ryan Davis', 'ryan@example.com', 'TESTER', 3, 3, NOW(), NOW()),
('Sophia Martinez', 'sophia@example.com', 'LEADERSHIP', 4, 4, NOW(), NOW()),
('Thomas White', 'thomas@example.com', 'PLATFORM', 4, 4, NOW(), NOW()),
('Ella Roberts', 'ella@example.com', 'DEVELOPER', 5, 5, NOW(), NOW()),
('James Carter', 'james@example.com', 'TESTER', 5, 5, NOW(), NOW()),
('Liam Scott', 'liam@example.com', 'LEADERSHIP', 6, 6, NOW(), NOW()),
('Ava Miller', 'ava@example.com', 'PLATFORM', 6, 6, NOW(), NOW());

-- Tasks
INSERT INTO tasks (title, description, status, priority, due_date, created_at, updated_at, project_id, team_id, user_id) VALUES
('AI Workflow Optimization', 'Enhance the AI automation pipeline', 'IN_PROGRESS', 'HIGH', '2025-05-15', NOW(), NOW(), 1, 1, 1),
('Cloud Data Encryption', 'Implement encryption for cloud storage', 'TODO', 'CRITICAL', '2025-05-22', NOW(), NOW(), 2, 2, 3),
('Performance Testing', 'Test system performance under high load', 'DONE', 'MEDIUM', '2025-04-30', NOW(), NOW(), 1, 1, 2),
('Firewall Configuration', 'Set up advanced security rules', 'BLOCKED', 'HIGH', '2025-05-08', NOW(), NOW(), 3, 3, 5),
('IoT Sensor Calibration', 'Optimize sensors for accuracy', 'TODO', 'CRITICAL', '2025-06-10', NOW(), NOW(), 4, 4, 7),
('DevSecOps Integration', 'Embed security in CI/CD process', 'IN_PROGRESS', 'HIGH', '2025-05-18', NOW(), NOW(), 3, 3, 6),
('AI Model Fine-Tuning', 'Improve precision of AI predictions', 'DONE', 'MEDIUM', '2025-04-29', NOW(), NOW(), 1, 1, 1),
('Cloud Cost Optimization', 'Reduce unnecessary cloud expenses', 'IN_PROGRESS', 'HIGH', '2025-05-12', NOW(), NOW(), 2, 2, 3),
('Blockchain Security Audit', 'Review security vulnerabilities in blockchain', 'TODO', 'CRITICAL', '2025-06-15', NOW(), NOW(), 5, 5, 9),
('Neural Network Optimization', 'Enhance ML models for accuracy', 'IN_PROGRESS', 'HIGH', '2025-06-20', NOW(), NOW(), 6, 6, 11),
('Decentralized Identity Verification', 'Build secure identity verification using blockchain', 'DONE', 'MEDIUM', '2025-05-25', NOW(), NOW(), 5, 5, 10),
('AI Chatbot Deployment', 'Deploy AI chatbot for customer service', 'BLOCKED', 'HIGH', '2025-07-01', NOW(), NOW(), 6, 6, 12);

-- Comments
INSERT INTO comments (content, created_at, task_id, user_id) VALUES
('AI model optimization completed successfully!', NOW(), 1, 1),
('Cloud encryption method selected, implementation next', NOW(), 2, 3),
('Performance tests show a 20% improvement', NOW(), 3, 2),
('Firewall rules need revision for compliance', NOW(), 4, 5),
('Calibration shows better accuracy in sensor readings', NOW(), 5, 7),
('DevSecOps security checks integrated into CI/CD', NOW(), 6, 6),
('AI model predictions are now 95% accurate', NOW(), 7, 1),
('Cloud cost optimization saved 30% of budget', NOW(), 8, 3),
('Blockchain security vulnerabilities patched', NOW(), 9, 9),
('Neural network models improved accuracy by 15%', NOW(), 10, 11),
('Decentralized identity verification successfully tested', NOW(), 11, 10),
('AI chatbot deployment blocked due to API issues', NOW(), 12, 12);