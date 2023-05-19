//    Revision Tool, a customisable revision resource.
//    Copyright (C) 2023  Arun Fletcher
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.ajsf;

public class InvalidSourceException extends Exception {
    public InvalidSourceException(String source) {
        super(String.format("No question found with source: '%s'.", source));
    }
}
