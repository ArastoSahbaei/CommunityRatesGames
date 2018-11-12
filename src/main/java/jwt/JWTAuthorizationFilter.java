package jwt;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public static final String TOKEN_PREFIX     = "Bearer ";
    public static final String HEADER_STRING    = "Authorization";
    private UserService userService;


    @Autowired
    public JWTAuthorizationFilter(AuthenticationManager authManager, ApplicationContext ctx) {
        super(authManager);
        this.userService = ctx.getBean(UserService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        if (authentication == null) {
            res.setStatus(401); // UNAUTHORIZED
            res.setHeader("Error", "unauthorized");
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }



    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = JWTUtility.parseUsername(token);
            if (user != null) {
                String userRoleAuth = userService.FindUserByUserName(user).getRole();//"test"; //authorityService.getUserRole(user);
                List<GrantedAuthority> grantedAuths = getGrantedAuthList(userRoleAuth);
                return new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
            }
        }
        return null;
    }

    private List<GrantedAuthority> getGrantedAuthList(String userRoleAuth) {
        if (userRoleAuth == null) return new ArrayList<>();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(userRoleAuth);
    }

}


