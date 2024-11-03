-- 상위 카테고리 삽입
INSERT INTO category (name, slug, image_url, created_date) VALUES
                                                               ('아우터', NULL, NULL, NOW()),
                                                               ('상의', NULL, NULL, NOW()),
                                                               ('신발', NULL, NULL, NOW()),
                                                               ('하의', NULL, NULL, NOW()),
                                                               ('가방', NULL, NULL, NOW()),
                                                               ('지갑', NULL, NULL, NOW()),
                                                               ('시계', NULL, NULL, NOW()),
                                                               ('주얼리 및 잡화', NULL, NULL, NOW()),
                                                               ('컬렉터블', NULL, NULL, NOW()),
                                                               ('뷰티', NULL, NULL, NOW()),
                                                               ('테크', NULL, NULL, NOW()),
                                                               ('캠핑', NULL, NULL, NOW()),
                                                               ('가구/리빙', NULL, NULL, NOW());

-- 아우터 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '자켓', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '아우터') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '아노락', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '아우터') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '코트', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '아우터') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 아우터', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '아우터') AS temp;

-- 상의 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '반소매 티셔츠', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '긴소매 티셔츠', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '가디건', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '셔츠', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '후드 집업', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스웨트셔츠', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '슬리브리스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '원피스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 상의', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '상의') AS temp;

-- 신발 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스니커즈', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '샌들/슬리퍼', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '플랫', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '로퍼', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '더비/레이스업', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '힐/펌프스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '부츠', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 신발', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '신발') AS temp;

-- 하의 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '바지', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '하의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '반바지', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '하의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스커트', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '하의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '레깅스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '하의') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 하의', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '하의') AS temp;

-- 가방 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '프리미엄가방', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '미니백', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '백팩', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '숄더백', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '토트백', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '크로스백', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '클러치', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '더플백', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '에코백', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '캐리어', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 가방', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가방') AS temp;

-- 지갑 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '반지갑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '지갑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '장지갑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '지갑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '카드 지갑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '지갑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '체인 지갑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '지갑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 지갑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '지갑') AS temp;

-- 시계 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '프리미엄시계', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '시계') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '전자 시계', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '시계') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '가죽 시계', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '시계') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '메탈 시계', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '시계') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 시계', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '시계') AS temp;

-- 주얼리 및 잡화 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '귀걸이', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '목걸이', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '팔찌', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '반지', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 주얼리', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '비니', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '버킷햇', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '볼캡', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 모자', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '벨트', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '아이웨어', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '머플러', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스카프', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '키링', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '넥타이', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '장갑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '양말', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 패션잡화', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '주얼리 및 잡화') AS temp;

-- 컬렉터블 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '레고', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '컬렉터블') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '베어브릭', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '컬렉터블') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '카우스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '컬렉터블') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '트레이딩 카드', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '컬렉터블') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT 'LP/CD', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '컬렉터블') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 컬렉터블', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '컬렉터블') AS temp;

-- 뷰티 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '니치 향수', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '패션 향수', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '립 메이크업', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '베이스 메이크업', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스킨케어', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '바디케어', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 뷰티', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '뷰티') AS temp;

-- 테크 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스마트폰', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스마트워치', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '케이스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '태블릿', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '노트북', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '헤어', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '헤드폰/이어폰', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '스피커', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '그래픽카드', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '키보드', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '마우스', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '게임기', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '게임 타이틀', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '주변기기', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '리퍼비시', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 테크', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '테크') AS temp;

-- 캠핑 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '텐트', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '캠핑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '체어', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '캠핑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '컵/보틀', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '캠핑') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 캠핑', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '캠핑') AS temp;

-- 가구/리빙 하위 카테고리
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '조명', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가구/리빙') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '의자', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가구/리빙') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '쿠션', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가구/리빙') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '러그', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가구/리빙') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '선반', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가구/리빙') AS temp;
INSERT INTO category (name, slug, image_url, created_date, parent_id)
SELECT '기타 가구/리빙', NULL, NULL, NOW(), category_id FROM (SELECT category_id FROM category WHERE name = '가구/리빙') AS temp;
