package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the Book Schema in <a href="https://fakerestapi.azurewebsites.net/index.html">FakeRestAPI</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String dateTime;

    public static BookDTO getDefaultWithRandomId() {
        return new BookDTO(
                (int) (Math.random() * 9_000_000) + 1_000_000, // Generate random number from 1,000,000 to 9,999,999
                "QA Title",
                "QA Description",
                300,
                "QA Excerpt",
                "2024-01-01T10:00:00"
        );
    }

    public static BookDTO getEditedBookDTO() {
        return new BookDTO(
                1, // Already existing id
                "QA Title - edited",
                "QA Description - edited",
                400,
                "QA Excerpt - edited",
                "2024-01-01T10:00:00 - edited"
        );
    }

    public static BookDTO getAlreadyInsertedBookDTO() {
        return new BookDTO(
                1,
                "Book 1",
                "Molestie clita erat et rebum ea lorem sed stet voluptua praesent ut. Sea assum vero dolor " +
                        "eros takimata consectetuer. Sea amet lorem at facilisis erat quis. Vero aliquip invidunt et " +
                        "amet ea erat et. Ipsum augue justo. Molestie at volutpat dolore cum est et aliquam et ipsum " +
                        "no in sea. Est duis gubergren takimata et. Dolor duo ipsum nonumy rebum dolor sit nobis null" +
                        "a aliquyam at diam option in lorem consequat. Voluptua rebum ipsum elitr rebum. Labore dolor" +
                        "e ea dolores wisi accusam diam laoreet consetetur takimata ipsum voluptua sed gubergren faci" +
                        "lisis sea ut. Dolor vero invidunt sit elitr invidunt magna dolor at ut sit dolor dolor elitr" +
                        " accusam est.\\n",
                100,
                "Sit nonumy diam et kasd clita voluptua takimata no dolor no amet soluta dolores ut dolor sit" +
                        " consequat. Voluptua dolor dolor. Rebum erat lorem dolor feugiat erat labore no. Eum ut sed" +
                        " magna magna hendrerit vero erat vel eos feugait molestie. Nam no lorem dolor nobis diam lo" +
                        "rem. Et sed ea ea sed takimata justo eum aliquyam kasd et facilisis zzril et erat elitr. Ip" +
                        "sum ex enim sadipscing sit voluptua duo justo enim takimata feugiat sea. In at eu nonummy a" +
                        "liquyam consetetur invidunt. Minim gubergren no ea. Elit nonumy sanctus stet erat aliquam e" +
                        "os erat amet. Illum accumsan ut aliquyam erat eos magna magna magna amet augue tempor vero " +
                        "duo. Amet consetetur clita. Justo voluptua sed magna commodo ea. Ipsum rebum sea velit dolo" +
                        "r vero amet dolore nam ea stet tempor est tempor magna. Nulla elitr aliquam blandit. Eu con" +
                        "sequat suscipit gubergren eos et vero dolor erat voluptua luptatum takimata diam sed. Sit a" +
                        "met dolor adipiscing consequat eos gubergren velit amet eos at.\\nJusto eos nibh labore sit" +
                        " consectetuer elit no eos dolore lorem accumsan dignissim. Et qui eirmod diam sanctus amet " +
                        "elit vero duo sit. Duo sadipscing tempor et clita tempor takimata kasd vero vero sadipscing" +
                        " amet elitr takimata labore dolore. Lorem id kasd no vero accusam te magna consequat magna " +
                        "sadipscing elit ut feugiat amet. Erat gubergren volutpat rebum lorem et sadipscing blandit " +
                        "sea no velit at. Dolore luptatum no lorem iriure consectetuer et accusam tempor duo volutpa" +
                        "t voluptua et eirmod dolores zzril elit. Diam et eirmod diam. Duo kasd amet. Diam enim et d" +
                        "olor nihil takimata eos nonumy iriure et. Dolor vero lobortis et diam et amet dolor sit lor" +
                        "em et ut dolores. Iusto erat iriure iusto velit magna et commodo iusto sit gubergren eirmod" +
                        " facilisis. Diam lorem invidunt in et sea eirmod lobortis dolore gubergren eirmod. Stet sed" +
                        " et ipsum minim diam lorem lorem tempor ea est consectetuer vel nonumy vero magna. Vero ips" +
                        "um consetetur. Lorem nonummy erat sadipscing ipsum duo vel invidunt dolor ut eirmod et ipsu" +
                        "m vel sanctus augue suscipit dignissim justo. Eum sed dolores ea tempor commodo mazim ut te" +
                        "mpor illum te et sea. Diam eirmod ipsum consetetur ad enim voluptua euismod soluta ea no mo" +
                        "lestie aliquip dolor tincidunt.\\nKasd autem aliquyam ut dolor diam zzril. Invidunt labore " +
                        "kasd et vel rebum qui vel aliquam. Et nam elitr eum eu sit sit molestie volutpat magna nonu" +
                        "mmy lorem tincidunt consetetur volutpat sit facilisis ut gubergren. Consequat justo volutpa" +
                        "t dolor et consequat clita sed rebum feugiat voluptua. Et est accusam labore ut invidunt vo" +
                        "luptua sadipscing takimata commodo sit autem. Diam illum eros lorem lorem lorem consetetur." +
                        " Dolor facilisis no dolore zzril gubergren nulla nobis et duo ut duis dolores et kasd dolor" +
                        "es invidunt ut. Accusam autem sadipscing vero consequat ut eirmod consequat dolor illum jus" +
                        "to et ea ea et. Illum amet justo et euismod elit consectetuer id duo sit dolor accusam in d" +
                        "olor. Ipsum dolore eum diam no et elitr elitr labore diam gubergren erat tempor ipsum ut do" +
                        "lore dolore dolore et. Lorem invidunt nonumy lorem vel eos voluptua enim. Eros minim et. La" +
                        "bore ipsum magna accusam est nihil. Sed duo stet amet et eirmod eos lorem takimata et. Cum " +
                        "liber ut dolore ipsum invidunt. Erat et sed est ipsum sed. Delenit stet nobis vero justo et" +
                        " eu dolor ut est diam facilisi eum sea diam in et consetetur erat.\\nDiam dolore nulla mini" +
                        "m consetetur invidunt et in sed eu velit ipsum voluptua delenit. Lorem nonumy nibh te tempo" +
                        "r elit sit stet sit invidunt dolor voluptua sit sit ipsum ad. Ea et et labore nonumy. Sed i" +
                        "n invidunt vero diam qui tincidunt stet. Dolore est eirmod accusam nihil et no invidunt sit" +
                        " est sadipscing dolore hendrerit eos tempor. Diam ipsum dolor possim dolor dolor dolor ut a" +
                        "met sadipscing quis lorem dolor et voluptua. Stet kasd accusam amet tempor tempor diam esse" +
                        " magna sit eos at erat consequat et lorem vero sed et. Diam lorem sanctus dolor praesent vo" +
                        "lutpat duo autem. Vero et tempor consetetur amet ut ut sit eos sed et stet tempor dolor ea." +
                        " Et esse augue et dolore justo justo nonumy sit labore labore quis in. Enim magna et illum " +
                        "delenit qui sit sanctus sanctus ut amet te amet est elit amet magna sed. Dolores sea ea inv" +
                        "idunt.\\nEt sea ipsum clita sadipscing amet sit amet labore accumsan dolore vulputate invid" +
                        "unt. Labore accusam et vero sed. Id sit ut. Sed accusam dolores et delenit voluptua amet se" +
                        "d et consetetur amet duo aliquyam et est qui sed vel. Sanctus adipiscing consetetur magna n" +
                        "onumy eirmod dolor nonumy clita sit ut vero sit erat duis tempor. Sea dolor ullamcorper reb" +
                        "um. Eirmod velit sadipscing invidunt consetetur eirmod et exerci in ex vero sed sea adipisc" +
                        "ing eum. Diam dolores qui nulla commodo erat tempor et. Dolores ipsum tincidunt sea velit e" +
                        "t amet sed lorem dolor lorem et magna sit sea erat. Tation ipsum esse tempor invidunt no no" +
                        " eirmod lorem ipsum stet sit magna et et minim. Takimata dolor adipiscing diam vero duo lor" +
                        "em labore. Et invidunt tempor erat aliquam labore consectetuer volutpat erat erat elitr era" +
                        "t. Suscipit sadipscing et ipsum takimata esse diam lorem amet at et. Aliquyam nonumy esse t" +
                        "empor. Dolor amet qui eum in no est adipiscing justo iriure stet dolor et aliquip. Nonumy l" +
                        "obortis et. Takimata labore erat facilisi clita labore diam amet dolor aliquyam. Sadipscing" +
                        " minim luptatum hendrerit diam dolore consetetur diam.\\n",
                "2024-11-08T17:49:27.148"
        );
    }

}
