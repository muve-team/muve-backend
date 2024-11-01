package kr.muve.common.security;

import org.junit.jupiter.api.Test;

class JweTokenProviderTest {
        JweTokenProvider provider = new JweTokenProvider();

        @Test
        void test() {
            provider.validToken("eyJjdHkiOiJ0ZXh0L3BsYWluIiwiYWxnIjoiZGlyIiwiZW5jIjoiQTI1NkdDTSJ9..tEVVdTdwy2ZTLcLG.OymK7YotSFLLNQrkElSsT4TLfw9kJBgvcXQSHXby575EjszDULkUtYpFg_XlhidwLKS9kCA66jDtVREO0o4AOQY.zEQm1c7bk4HJca6U_cl__g");
        }
}